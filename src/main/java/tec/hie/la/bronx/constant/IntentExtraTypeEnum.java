package tec.hie.la.bronx.constant;

public enum IntentExtraTypeEnum {

    STRING("string"),
    INT("int"),
    BOOLEAN("boolean"),
    STRING_ARRAY("stringArray"),
    INT_ARRAY("intArray"),
    BOOLEAN_ARRAY("booleanArray");

    private String name;

    IntentExtraTypeEnum(String name) {
        this.name = name;
    }

    public static IntentExtraTypeEnum of(String value) {
        for (IntentExtraTypeEnum intentExtraTypeEnum : IntentExtraTypeEnum.values()) {
            if (intentExtraTypeEnum.name.equals(value)) {
                return intentExtraTypeEnum;
            }
        }
        return STRING;
    }
}
