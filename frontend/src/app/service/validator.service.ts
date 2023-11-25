import { Injectable } from '@angular/core';

interface optionValidate {
  form: Element;
  formGroupSelector: string;
  errorSelector: string;
  rules: Array<any>;
  onSubmit: any;
}

@Injectable({
  providedIn: 'root'
})


  
export class ValidatorService {
  constructor() {}

  initValidator(option: optionValidate) {
    function getParent(element: HTMLElement, selector: any){
      while (element.parentElement) { 
        if (element.parentElement.matches(selector)) {
          return element.parentElement;
        }
        element = element.parentElement;
      }
      return null;
    }


    let selectorRules: any = {};

    // hàm thực hiện validate
    function validate(inputElement: HTMLInputElement, rule: any) {
      let errorElement = getParent(inputElement, option.formGroupSelector)?.querySelector(option.errorSelector) as HTMLElement;
      let errorMessage;
      
      // lấy ra các rules của selector. Lặp qua từng rule và kiểm tra. Nếu có lỗi thì dừng luôn
      let rules = selectorRules[rule.selector]
      for (let i = 0; i < rules.length; i++){
        switch (inputElement.type) {
          case 'radio':
          case 'checkbox':
            errorMessage = rules[i](formElement.querySelector(rule.selector + ':checked'));
            break;
          default:
            errorMessage = rules[i](inputElement.value)
            break;
        }
        if (errorMessage) break;
      }

      // if (!(inputElement instanceof HTMLInputElement) || !(inputElement instanceof HTMLSelectElement)) {
      //   return; // Không thực hiện xử lý nếu không phải là HTMLInputElement
      // }
      
      if (errorMessage) { 
        errorElement.innerText = errorMessage;
        getParent(inputElement, option.formGroupSelector)?.classList.add('invalid');
      } else {
        errorElement.innerText = '';
        getParent(inputElement, option.formGroupSelector)?.classList.remove('invalid');
      }

      return !errorMessage;
    }

    let formElement = option.form;

    if (formElement) {
      formElement.addEventListener('submit', (e) => {
        e.preventDefault();

        let isFormValid = true;

        // Thực hiện lặp qua từng rule và validate luôn
        option.rules.forEach((rule) => {
          let inputElement = formElement.querySelector(rule.selector);
          let isValid = validate(inputElement, rule);

          if (!isValid) { 
            isFormValid = false;
          }
        });

        let enableinputs = formElement.querySelectorAll('[name]');

        if (isFormValid) {
          if (typeof option.onSubmit === 'function') { 
            let formValues = Array.from(enableinputs).reduce((values: any, input: any) => {
              switch (input.type) {
                case 'radio':
                  if (input.matches(':checked')) {
                    values[input.name] = input.value;
                  }
                  break;
                case 'checkbox':
                  if (input.matches(':checked')) {
                    if (values[input.name] === undefined || values[input.name] === null) {
                      values[input.name] = [];
                    }
                    
                    values[input.name].push(input.value);
                    return values;
                  };
                  break;
                case 'file':
                  values[input.name] = input.files;
                  break;
                default:
                  values[input.name] = input.value;
                  break;
              }

              return values;
            }, {});
            option.onSubmit(formValues)
          }
        } 
      })

      option.rules.forEach((rule) => {
        // Lưu lại các rules cho mỗi input
        
        if (Array.isArray(selectorRules[rule.selector])) {
          selectorRules[rule.selector].push(rule.test)
        } else {
          selectorRules[rule.selector] = [rule.test];
        }

        let inputElements = formElement.querySelectorAll(rule.selector)

        Array.from(inputElements).forEach(inputElement => { 
          
          // Xử lý trường hợp blur khỏi input
          inputElement.addEventListener('blur', () => {
            validate(inputElement, rule);
          })
          
          // xử lý mỗi khi người dùng nhập vào input
          inputElement.addEventListener('input', () => {
            let errorElement = getParent(inputElement, option.formGroupSelector)?.querySelector(option.errorSelector) as HTMLElement;
            errorElement.innerText = '';
            getParent(inputElement, option.formGroupSelector)?.classList.remove('invalid');
          })
        })

      })
    }

  }

  // Các rules validate
  isRequired(selector: string, message?: string) {
    return {
      selector,
      test: (value: any) => {
        return value ? undefined : message || 'Vui lòng nhập trường này';
      },
    };
  }

  isEmail(selector: string, message?: string) {
    return {
      selector,
      test: (value: any) => {
        const regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        return regex.test(value) ? undefined : message || 'Trường này phải là email';
      },
    };
  }

  minLength(selector: string, min: number, message?: string) {
    return {
      selector,
      test: (value: any) => {
        return value.length >= min
          ? undefined
          : message || `Vui lòng nhập tối thiểu ${min} ký tự`;
      },
    };
  }

  maxLength(selector: string, max: number, message?: string) {
    return {
      selector,
      test: (value: any) => {
        return value.length <= max
          ? undefined
          : message || `Vui lòng nhập tối đa ${max} ký tự`;
      },
    };
  }

  isConfirmed(selector: string, conFirmRef: HTMLInputElement, message?: string) {
    return {
      selector,
      test: (value: any) => {
        return value === conFirmRef.value ? undefined : message || 'Giá trị nhập vào không chính xác';
      },
    };
  }
}