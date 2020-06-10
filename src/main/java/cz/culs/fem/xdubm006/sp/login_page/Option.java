package cz.culs.fem.xdubm006.sp.login_page;

public enum Option {
    Admin, Player; //currently 2 choices of login

    public String value() {
        return name();
    }

    public static Option fromvalue(String v) {
        return valueOf(v);
    }
}
