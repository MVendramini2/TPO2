package C8_EJ7;

public class Main {
    public static void main(String[] args) {
        Dictionary<Integer, Character> d = new LinkedDictionaryABB<>();
        d.put(5, 'A'); d.put(7, 'B'); d.put(2, 'C'); d.put(8, 'D'); d.put(2, 'E');
        System.out.println("size claves = " + d.size());
        System.out.print("get(2) -> ");
        Iterable<Character> it = d.get(2);
        if (it != null) for (char c : it) System.out.print(c + " ");
        System.out.println();


        System.out.println("remove(2,'C') => " + d.remove(2, 'C'));
        System.out.print("get(2) -> "); it = d.get(2);
        if (it != null) for (char c : it) System.out.print(c + " ");
        System.out.println();


        System.out.print("keys -> "); for (int k : d.keys()) System.out.print(k + " "); System.out.println();
        System.out.print("remove(5) -> "); for (char c : d.remove(5)) System.out.print(c + " "); System.out.println();
        System.out.println("size claves = " + d.size());
    }
}
