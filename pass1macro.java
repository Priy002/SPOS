package pass1macro;

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

    MDT() {
        index = 0;
        def = new String[4];
    }

    void print() {
        if (def[0] != null)
            System.out.print(def[0] + " ");
        if (def[1] != null)
            System.out.print(def[1] + " ");
        if (def[2] != null)
            System.out.print(def[2] + " ");
        if (def[3] != null)
            System.out.print(def[3] + " ");
        System.out.println();
    }
}

public class pass1macro {
    public static void main(String args[]) {
        String input[][] = { { "MACRO", "INCR", "&A", null, "&REG" },
                { null, "MOVER", "REG", "&B", null },
                { null, "SUBS", "&A", "&B", null },
                { null, "MOVEM", "&REG", "&A", null },
                { "MEND", null, null, null, null },
                { "MACRO", "ADDS", "&F", "&S", null },
                { null, "MOVER", "AREG", "&F", null },
                { null, "MULT", "AREG", "&S", null },
                { null, "MOVEM", "AREG", "&S", null },
                { null, "WRITE", "&S", null, null },
                { "MEND", null, null, null, null },
                { "MACRO", "ADDS", null, "&S", null },

                { null, "MOVER", "AREG", "&F", null },
                { null, "SUB", "BREG", "&F", null },
                { null, "MOVEM", "BREG", "&S", null },
                { null, "WRITE", "&S", null, null },
                { "MEND", null, null, null, null },
                { null, "START", null, "200", null },
                { null, "READ", "N1", null, null },
                { null, "READ", "N2", null, null },
                { null, "ADDS", null, "N2", null },
                { null, "READ", "N1", "N2", null },
                { null, "INCR", "N1", "N2", "DREG" },
                { null, "STOP", null, null, null },
                { "N1", "DS", "2", null, null },
                { "N2", "MULT", "2", null, null },
                { null, "END", null, null, null } };
        MNT n[] = new MNT[20];
        MDT d[] = new MDT[20];
        int i = 0, nc = 0, dc = 0;
        String arr1, arr2, arr3, arr4, arr5;
        while (i < input.length) {
            arr1 = null;
            arr2 = null;
            arr3 = null;
            arr4 = null;
            arr5 = null;
            if (input[i][0] != null)
                arr1 = input[i][0];
            if (arr1 == null)
                arr1 = "null";
            if (arr1.equals("MACRO")) {
                if (input[i][1] != null)
                    arr2 = input[i][1];
                if (input[i][2] != null)
                    arr3 = input[i][2];
                if (input[i][3] != null)
                    arr4 = input[i][3];
                if (input[i][4] != null)
                    arr5 = input[i][4];
                n[nc] = new MNT(nc, arr2, dc);
                nc++;
                while (!arr1.equals("MEND")) {
                    d[dc] = new MDT();
                    d[dc].index = dc;
                    d[dc].def[0] = arr2;
                    if (!arr1.equals("MACRO")) {
                        if (arr3 != null) {

                            if (arr3.equals(d[n[nc - 1].def_index].def[1]))
                                arr3 = "#1";
                            else if (arr3.equals(d[n[nc - 1].def_index].def[2]))
                                arr3 = "#2";
                            else if (arr3.equals(d[n[nc - 1].def_index].def[3]))
                                arr3 = "#3";
                        }
                        if (arr4 != null) {
                            if (arr4.equals(d[n[nc - 1].def_index].def[1]))
                                arr4 = "#1";
                            else if (arr4.equals(d[n[nc - 1].def_index].def[2]))
                                arr4 = "#2";
                            else if (arr4.equals(d[n[nc - 1].def_index].def[3]))
                                arr4 = "#3";
                        }
                        if (arr5 != null) {
                            if (arr5.equals(d[n[nc - 1].def_index].def[1]))
                                arr5 = "#1";
                            else if (arr5.equals(d[n[nc - 11].def_index].def[2]))
                                arr5 = "#2";
                            else if (arr5.equals(d[n[nc - 11].def_index].def[3]))
                                arr5 = "#3";
                        }
                    }
                    if (arr3 != null)
                        d[dc].def[1] = arr3;
                    if (arr4 != null)
                        d[dc].def[2] = arr4;
                    if (arr5 != null)
                        d[dc].def[3] = arr5;
                    dc++;
                    i++;
                    arr1 = arr2 = arr3 = arr4 = arr5 = null;
                    if (input[i][0] != null)
                        arr1 = input[i][0];
                    if (input[i][1] != null)
                        arr2 = input[i][1];
                    if (input[i][2] != null)
                        arr3 = input[i][2];
                    if (input[i][3] != null)
                        arr4 = input[i][3];
                    if (input[i][4] != null)
                        arr5 = input[i][4];
                    if (arr1 == null)
                        arr1 = "null";
                }
                if (arr1.equals("MEND")) {
                    d[dc] = new MDT();
                    d[dc].index = dc;

                    d[dc].def[0] = arr1;
                    dc++;
                }
            }
            i++;
        }
        System.out.print("MNT\nIndex\tName\tMDT_Index\n");
        for (i = 0; i < nc; i++) {
            System.out.println(n[i].index + "\t" + n[i].name + "\t" + n[i].def_index);
        }
        System.out.print("\n\nMDT\nIndex\tDefinition\n");
        for (i = 0; i < dc; i++) {
            System.out.print(d[i].index + "\t");
            d[i].print();
        }
    }
}