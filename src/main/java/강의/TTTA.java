package 강의;

public class TTTA {
    private String data;
    private Integer kk;

    public TTTA() {
    }

    public TTTA(Integer kk) {
        this.kk = kk;
    }

    public TTTA(String data) {
        this.data = data;
    }

    public TTTA(String data, Integer kk) {
        this.data = data;
        this.kk = kk;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getKk() {
        return kk;
    }

    public void setKk(Integer kk) {
        this.kk = kk;
    }

    @Override
    public String toString() {
        return data;
//        return "TTTA{" +
//            "data='" + data + '\'' +
//            ", kk=" + kk +
//            '}';
    }

    public static void main(String[] args) {
        TTTA ttta = new TTTA();
        TTTA tttat = new TTTA(3);
        String data = tttat.getData();
        System.out.println("data = " + data);
        System.out.println(tttat);
    }
}