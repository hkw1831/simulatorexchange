package message;

public class DMAMsgRejectCode
{
    public static final Integer NORMAL = new Integer(0);
    public static final Integer INVALID_MSG_TYPE = new Integer(90);
    public static final Integer ORDER_NOT_FOUND = new Integer(91);
    public static final Integer INVALID_QUANTITY = new Integer(92);
    public static final Integer INVALID_SIDE = new Integer(93);
    public static final Integer INVALID_ORDER_TYPE = new Integer(94);
    public static final Integer INVALID_PRICE = new Integer(95);
    public static final Integer MISSING_PRICE_FOR_LIMIT_ORDER = new Integer(96);
    public static final Integer INVALID_CLORDID = new Integer(97);
    public static final Integer INVALID_TIME_IN_FORCE = new Integer(98);
    public static final Integer OTHER = new Integer(99);
}
