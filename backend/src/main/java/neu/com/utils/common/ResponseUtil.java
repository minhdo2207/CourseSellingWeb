package neu.com.utils.common;

import neu.com.vo.converter.PagingConverter;
import neu.com.vo.request.SortingAndPagingRequestVO;
import neu.com.vo.response.PagedResult;
import neu.com.vo.response.PagedVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import neu.com.utils.Constants;
import neu.com.utils.Translator;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public final class ResponseUtil {
    private static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    private ResponseUtil() {
    }

    public static ResponseEntity<Map<String, Object>> setResponseData(Integer statusCodeAsInt, Object data,
                                                                      String message) {
        Map<String, Object> responseData = new HashMap<>();

        String responseMessage = getResponseMessage(statusCodeAsInt, false);
        if (StringUtils.isEmpty(message)) {
            responseMessage = message;
        }

        responseData.put("message", responseMessage);

        if (statusCodeAsInt == null || HttpStatus.resolve(statusCodeAsInt) == null
                || HttpStatus.resolve(statusCodeAsInt).is5xxServerError()) {
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpStatus statusCode = HttpStatus.resolve(statusCodeAsInt);
        if (statusCode.is2xxSuccessful() && data != null) {
            responseData.put("data", data);
        }

        return new ResponseEntity<>(responseData, statusCode);
    }

    public static ResponseEntity<Map<String, Object>> setLogonResponseData(Integer statusCodeAsInt, Object data,
                                                                           String message) {
        Map<String, Object> responseData = new HashMap<>();

        String responseMessage = getResponseMessage(statusCodeAsInt, false);
        if (StringUtils.isEmpty(message)) {
            responseMessage = message;
        }

        responseData.put("message", responseMessage);

        if (statusCodeAsInt == null || HttpStatus.resolve(statusCodeAsInt) == null
                || HttpStatus.resolve(statusCodeAsInt).is5xxServerError()) {
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HttpStatus statusCode = HttpStatus.resolve(statusCodeAsInt);
        if (statusCode.is2xxSuccessful() && data != null) {
            responseData.put("data", data);
        }

        return new ResponseEntity<>(responseData, statusCode);
    }

    public static ResponseEntity<Map<String, Object>> setEmptyDataResponse(Integer statusCodeAsInt) {
        return setResponseData(statusCodeAsInt, null, null);
    }

    public static ResponseEntity<Map<String, Object>> setResponseInvalidData(String message) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("message", message);

        return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
    }

    private static String getResponseMessage(Integer statusCodeAsInt, boolean isLogon) {
        if (statusCodeAsInt == null || HttpStatus.resolve(statusCodeAsInt) == null
                || HttpStatus.resolve(statusCodeAsInt).is5xxServerError()) {
            return Translator.toLocale("msg_error_server");
        }

        HttpStatus statusCode = HttpStatus.resolve(statusCodeAsInt);
        if (statusCode.is2xxSuccessful()) {
            return Translator.toLocale("msg_success");
        }

        if (statusCode.is4xxClientError()) {
            if (statusCode.equals(HttpStatus.UNAUTHORIZED)) {
                return isLogon ? Translator.toLocale("msg_error_login_fail")
                        : Translator.toLocale("msg_error_unauthorized");
            }

            if (statusCode.equals(HttpStatus.FORBIDDEN)) {
                return Translator.toLocale("msg_forbidden");
            }

            if (statusCode.equals(HttpStatus.CONFLICT)) {
                return Translator.toLocale("msg_error_conflict");
            }

            return Translator.toLocale("msg_error_wagby_server") + statusCode.value();
        }

        return Translator.toLocale("msg_error_unknown");
    }

    public static boolean isSuccessResponse(Integer statusCode) {
        if (statusCode == null) {
            return false;
        }

        return statusCode >= 200 && statusCode < 300;
    }

    public static PagedVO convertPageDataToPagedVO(Page<?> page) {
        PagedVO pagedVO = new PagedVO();

        // set PagedVO
        pagedVO.setTotalPage(page.getTotalPages());
        pagedVO.setPageSize(page.getTotalPages());
        pagedVO.setTotalRecord(page.getTotalElements());
        pagedVO.setCurrentPage(
                page.getPageable().isUnpaged() ? 0 : page.getPageable().getPageNumber() + Constants.PAGE_NUM);
        return pagedVO;
    }

    public static <R, U> List<R> convertQueriedDataToResponseDto(List<U> data,
                                                                 Function<Optional<U>, R> dataToResponseMapper) {
        if (data != null && !data.isEmpty()) {
            return data.stream().map(Optional::ofNullable).map(dataToResponseMapper).toList();
        }
        return List.of();
    }

    public static <R, U> PagedResult<R> commonPaging(SortingAndPagingRequestVO pagingRequestVO, String defaultSortKey,
                                                     Function<Pageable, Page<U>> jpaQueryMethod, Function<List<U>, List<R>> dataToResponseMapper) {
        // Set param paging
        SortingAndPagingRequestVO sort = PagingConverter.getPagingRequestVO(pagingRequestVO, defaultSortKey);

        Pageable paging;
        if (sort.getPage() < 0) {
            // get all not page
            paging = Pageable.unpaged();
        } else {
            paging = PageRequest.of(sort.getPage(), sort.getSize(),
                    Sort.by(Sort.Direction.valueOf(sort.getSortDir()), sort.getSortKey()));
        }

        // Get data pageable
        Page<U> page = jpaQueryMethod.apply(paging);

        PagedResult<R> pagedResult = new PagedResult<>();
        pagedResult.setPaging(convertPageDataToPagedVO(page));
        pagedResult.setElements(dataToResponseMapper.apply(page.getContent()));

        return pagedResult;
    }
}
