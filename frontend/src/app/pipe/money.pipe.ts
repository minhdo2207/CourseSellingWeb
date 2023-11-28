import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'money'
})
export class MoneyPipe implements PipeTransform {

  transform(value: number): string {
    // Sử dụng CurrencyPipe để định dạng số tiền
    const currencyPipe = new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'USD',
      minimumFractionDigits: 0
    });

    // Chuyển đổi số tiền thành định dạng chuỗi
    const formattedValue = currencyPipe.format(value);

    return formattedValue;
  }

}
