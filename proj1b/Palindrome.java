public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> cad = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            cad.addLast(word.charAt(i));
        }
        return cad;
    }

    private boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.isEmpty() || deque.size() == 1) {
            return true;
        }
        Character first = deque.removeFirst();
        Character last = deque.removeLast();
        if (first != last) {
            return false;
        } else {
            return isPalindromeHelper(deque);
        }
    }

    public boolean isPalindrome(String word) {

        /* Recursive version */
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque);

        /* Iterative version */
//        Deque<Character> deque = wordToDeque(word);
//        if (deque.isEmpty() || deque.size() == 1) {
//            return true;
//        }
//        while (!deque.isEmpty()) {
//            Character first = deque.removeFirst();
//            Character last = deque.removeLast();
//            if (first != last) {
//                return false;
//            }
//            if (deque.size() == 1) {
//                return true;
//            }
//        }
//        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        if (deque.isEmpty() || deque.size() == 1) {
            return true;
        }
        while (!deque.isEmpty()) {
            Character first = deque.removeFirst();
            Character last = deque.removeLast();
            if (!cc.equalChars(first, last)) {
                return false;
            }
            if (deque.size() == 1) {
                return true;
            }
        }
        return true;
    }
}
