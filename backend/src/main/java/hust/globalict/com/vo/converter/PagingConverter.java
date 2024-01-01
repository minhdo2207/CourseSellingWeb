package hust.globalict.com.vo.converter;


import hust.globalict.com.utils.Constants;
import hust.globalict.com.utils.common.StringUtils;
import hust.globalict.com.vo.request.SortingAndPagingRequestVO;

public final class PagingConverter {

    private PagingConverter() {

    }

    public static SortingAndPagingRequestVO getPagingRequestVO(SortingAndPagingRequestVO pagingVO, String defaultSortKey) {
        SortingAndPagingRequestVO sortingAndPagingRequestVO = new SortingAndPagingRequestVO();
        sortingAndPagingRequestVO.setPage(pagingVO.getPage() > 0 ? pagingVO.getPage() - 1 : pagingVO.getPage());
        sortingAndPagingRequestVO.setSize(pagingVO.getSize() > 0 ? pagingVO.getSize() : Integer.valueOf(Constants.DEFAULT_SIZE));
        sortingAndPagingRequestVO.setSortKey(mapSortKey(pagingVO.getSortKey(), defaultSortKey));
        sortingAndPagingRequestVO.setSortDir(mapSortDir(pagingVO.getSortDir()));
        return sortingAndPagingRequestVO;
    }

    private static String mapSortKey(String sortKey, String defaultSortKey) {
        if (StringUtils.isEmpty(sortKey)) {
            return defaultSortKey;
        }

        return sortKey;
    }

    private static String mapSortDir(String sortDir) {
        if (StringUtils.isEmpty(sortDir)) {
            return Constants.PAGE_SORT_ASC;
        }

        return sortDir.toUpperCase();
    }
}
