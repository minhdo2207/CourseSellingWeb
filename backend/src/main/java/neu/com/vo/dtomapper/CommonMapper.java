package neu.com.vo.dtomapper;

import com.naharoo.commons.mapstruct.BidirectionalMapper;
import neu.com.utils.common.BooleanUtils;


import javax.inject.Named;

public interface CommonMapper<S, D> extends BidirectionalMapper<S, D> {

    @Named("stringToBoolean")
    default Boolean stringToBoolean(String s) {
        return BooleanUtils.toBooleanObject(s);
    }

    @Named("booleanToString")
    default String stringToBoolean(Boolean b) {
        return BooleanUtils.toCharacterObject(b);
    }

}
