package pass2macro;

class MNT {
    int index;
    String name;
    int def_index;

    MNT(int i, String a, int j) {
        index = i;
        name = a;
        def_index = j;
    }
}

class MDT {
    int index;
    String def[];

    MDT(int i, String m, String op1, String op2, String op3) {
        def = new String[4];
        def[0] = m;
        def[1] = op1;
        def[2] = op2;
        def[3] = op3;
    }

}

public class pass2macro {
    public static void main(String args[]) {
        String input[][] = {

                { null, "START", null, "200", null },
                { null, "READ", "N1", null, null },
                { null, "READ", "N2", null, null },
                { null, "ADDS", "N1", "N2", null },
                { null, "SUBS", "N1", "N2", null },
                { null, "INCR", "N1", "N2", "DREG" },
                { null, "STOP", null, null, null },
                { "N1", "DS", "2", null, null },
                { "N2", "DS", "2", null, null },
                { null, "END", null, null, null } };

        MNT n[] = new MNT[20];
        MDT d[] = new MDT[20];
        n[0] = new MNT(0, "INCR", 0);
        n[1] = new MNT(1, "ADDS", 5);
        n[2] = new MNT(2, "SUBS", 11);
        int nc = 3;
        d[0] = new MDT(0, "INCR", "&A", "&B", "&REG");
        d[1] = new MDT(1, "MOVER", "#3", "#1", null);
        d[2] = new MDT(2, "ADDS", "#1", "#2", null);

        d[3] = new MDT(3, "MOVEM", "#3", "#1", null);
        d[4] = new MDT(4, "MEND", null, null, null);
        d[5] = new MDT(5, "ADDS", "&F", "&S", null);
        d[6] = new MDT(6, "MOVER", "AREG", "#1", null);
        d[7] = new MDT(7, "ADD", "AREG", "#2", null);
        d[8] = new MDT(8, "MOVEM", "AREG", "#2", null);
        d[9] = new MDT(9, "WRITE", "#2", null, null);
        d[10] = new MDT(10, "MEND", null, null, null);
        d[11] = new MDT(11, "SUBS", "&F", "&S", null);
        d[12] = new MDT(12, "MOVER", "BREG", "#1", null);
        d[13] = new MDT(13, "SUB", "BREG", "#2", null);
        d[14] = new MDT(14, "MOVEM", "BREG", "#2", null);
        d[15] = new MDT(15, "WRITE", "#2", null, null);
        d[16] = new MDT(16, "MEND", null, null, null);
        int dc = 17;
        String output[][], arr1, arr2, arr3, arr4, arr5, op1, op2, op3;
        output = new String[dc + input.length][5];
        int i = 0, j = 0, k = 0;
        while (i < input.length) {
            j = 0;
            arr1 = arr2 = arr3 = arr4 = arr5 = null;
            if (input[i][1] != null)
                arr2 = input[i][1];
            for (j = 0; j < nc; j++) {
                if (arr2.equals(n[j].name)) {
                    break;
                }
            }
            if (j < nc) {
                if (input[i][0] != null)
                    arr1 = input[i][0];
                if (input[i][2] != null)
                    arr3 = input[i][2];
                if (input[i][3] != null)
                    arr4 = input[i][3];
                if (input[i][4] != null)
                    arr5 = input[i][4];
                j = n[j].def_index;
                j++;
                if (arr1 != null)
                    output[k][0] = arr1;
                while (!d[j].def[0].equals("MEND")) {
                    op1 = d[j].def[1];
                    op2 = d[j].def[2];
                    op3 = d[j].def[3];

                    if (d[j].def[0] != null)
                        output[k][1] = d[j].def[0];
                    if (d[j].def[1] != null) {
                        if (d[j].def[1].equals("#1"))
                            op1 = arr3;
                        else if (d[j].def[1].equals("#2"))
                            op1 = arr4;
                        else if (d[j].def[1].equals("#3"))
                            op1 = arr5;
                    }
                    if (d[j].def[2] != null) {
                        if (d[j].def[2].equals("#1"))
                            op2 = arr3;
                        else if (d[j].def[2].equals("#2"))
                            op2 = arr4;
                        else if (d[j].def[2].equals("#3"))
                            op2 = arr5;
                    }
                    if (d[j].def[3] != null) {
                        if (d[j].def[3].equals("#1"))
                            op3 = arr3;
                        else if (d[j].def[3].equals("#2"))
                            op3 = arr4;
                        else if (d[j].def[3].equals("#3"))
                            op3 = arr5;

                    }
                    output[k][2] = op1;
                    output[k][3] = op2;
                    output[k][4] = op3;
                    k++;
                    j++;
                }
                i++;
            } else {
                output[k] = input[i];
                i++;
                k++;
            }
        }
        for (i = 0; i < output.length; i++) {
            print(output[i][0], output[i][1], output[i][2], output[i][3], output[i][04]);
            System.out.println();
        }
    }

    static void print(String a, String b, String c, String d, String e) {
        if (a != null)
            System.out.print(a);
        System.out.print("\t");
        if (b != null)
            System.out.print(b);
        System.out.print("\t");

        if (c != null)
            System.out.print(c);
        System.out.print("\t");
        if (d != null)
            System.out.print(d);
        System.out.print("\t");
        if (e != null)
            System.out.print(e);
        System.out.print("\t");
    }
}