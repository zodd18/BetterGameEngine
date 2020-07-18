package model.utils;

public abstract class GLog {
    public static final int NONE = 0;
    public static final int INFO = 1;
    public static final int WARN = 2;
    public static final int MAX = 3;
    public static final int ALL = 4;

                     private static int SELECTED_LEVEL = NONE;
                                                                        /*
        +-----------------------------------------------------------+
        |                 |   InfoLevel hierarchy   |               |
        +-----------------------------------------------------------+
        |    0: []                                                  |
        |    1: [ERROR]                                             |
        |    2: [ERROR], [WARNING]                                  |
        |    3: [ERROR], [WARNING], [INFO]                          |
        |    4: [ERROR], [WARNING], [INFO], , [INPUT], {HITBOXES}   |
        +-----------------------------------------------------------+
    */

    public static void inputLog(Object msg) {
        if(SELECTED_LEVEL >= ALL) System.out.println("[INPUT]: " + msg);
    }

    public static void log(Object msg) {
        if(SELECTED_LEVEL >= MAX) System.out.println("[INFO]: " + msg);
    }

    public static void warningLog(Object msg) {
        if (SELECTED_LEVEL >= WARN) System.out.println("[WARNING]: " + msg);
    }

    public static void errorLog(Object msg) {
        if(SELECTED_LEVEL >= INFO) System.out.println("[ERROR]: " + msg);
    }

    public static void setSelectedLevel(int level) {
        SELECTED_LEVEL = level;
        if (SELECTED_LEVEL > ALL) SELECTED_LEVEL = NONE;
        else if (SELECTED_LEVEL < NONE) SELECTED_LEVEL = NONE;
    }

    public static int getSelectedLevel() { return SELECTED_LEVEL; }
}
