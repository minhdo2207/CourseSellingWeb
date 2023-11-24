package neu.com.utils;

public class Constants {
    public static final String STATUS_OK = "OK";
    public static final String STATUS_THANH_CONG = "Thành công";
    public static final String API_VERSION = "/v1";
    public static final String AUTHORIZATION_REQUEST_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    public static final String MA_VUNG_VN = "84";

    // Paging
    public static final String DEFAULT_PAGE = "0";

    public static final String DEFAULT_SIZE = "100";

    public static final String DEFAULT_SORT_KEY = null;

    public static final int PAGE_NUM = 1;

    public static final String PAGE_SORT_ASC = "ASC";

    public static final String PAGE_SORT_DESC = "DESC";

    // Locale
    public static final String DEFAULT_LOCALE = "vi";

    public static final String DEFAULT_ENCODING = "UTF-8";

    public static final String DEFAULT_BASENAME = "i18n/messages";

    /**
     * Default = DEFAU
     */
    public static final String SHOWLAYOUT_Default = "DEFAU";
    /**
     * Same On Top = ONTOP
     */
    public static final String SHOWLAYOUT_SameOnTop = "ONTOP";
    /**
     * Left And Right = LANDR
     */
    public static final String SHOWLAYOUT_LeftAndRight = "LANDR";

    public static final String ISTOOLBARBUTTON_Window = "N";

    public static final String PROPERTIE_LANGUAGE = "#AD_Language";

    public static final String PROPERTIE_LANG_DEFAULT = "vi_VN";

    public static final String PARENT_ID = "PARENT_ID";

    public static final String IS_ACTIVE = "ISACTIVE";

    public static final int WINDOW_ID_MASTER_DATA = 15200584;

    /* Format TIME */
    public static final String ISO_MONTH_SDF_PATTERN = "yyyy-MM";

    public static final String MONTH_SDF_PATTERN = "MM-yyyy";

    public static final String DAY_SDF_PATTERN = "dd-MM-yyyy";

    public static final String ISO_DATE_SDF_PATTERN = "yyyy-MM-dd";

    public static final String ISO_TIME_SDF_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String ISO_TIME_TZ_SDF_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static final String FORMAT_DATE_TIME = "dd-MM-yyyy HH:mm:ss";

    public static final String FORMAT_DATE_TIME_REPORT = "dd-MM-yyyy HH:mm";

    public static final String FORMAT_DATE_IMPORT = "dd-MM-yyyy";

    public static final String FORMAT_DATE_TIME_MAIL = "HH:mm - dd-MM-yyyy";

    public static final String FORMAT_DATE_TIME_MOBILE = "dd-MM-yyyy HH:mm:ss";

    public static final String FORMAT_DATE_MOBILE = "dd-MM-yyyy";
    public static final String FORMAT_DATE_MOBILE_SSO = "dd/MM/yyyy";

    public static final String FORMAT_DATE_CDR = "yyyy-MM-dd'-'HH:mm:ss";

    /* Status */
    public static final String STATUS_YES = "Y";

    public static final String STATUS_NO = "N";

    public static final String EMAIL_PATTERN = ".*@gmail\\.com$";

    public static final String TOKEN_TYPE = "Bearer ";

    public static final String EMAIL = "Email";

    public static final String SMS = "SMS";

    public static final String IS_NOT_DELETED = "is_deleted=false";

    public static final String UPDATE_IS_DELETED = "UPDATE ad_user SET is_deleted = yes";

    /* Sample value */
    public static final int SAMPLE_CREATEDBY = 0;

    public static final int SAMPLE_UPDATEDBY = 0;

    public static final String SAMPLE_PASSWORD = "123456";

    public static final boolean STATUS_TRUE = true;

    public static final boolean STATUS_FALSE = false;

    public static final int DYNAMICFIELD_FIELDTYPE_MIN = 1;

    public static final int DYNAMICFIELD_FIELDTYPE_MAX = 3;

    public static final int ORG_LEVELNO_MIN = 1;

    public static final int ORG_LEVELNO_MAX = 5;
    public static final String DEFAULT_PASSWORD = "123456";
    public static final String DEFAULT_BLANK_PASSWORD = " ";

    public static final String VALID_PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";

    /* LOGIN FAILURE */
    public static final int MAX_FAILED_USERNAME_PASSWORD_LOGIN_NUMBER = 5;

    public static final long EXTERNAL_USER_LOCK_TIME_DURATION = 5 * 60 * 1000; // 5 minutes


}

