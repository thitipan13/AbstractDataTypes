import java.util.ArrayList;

/**
 * ADT ที่เก็บ integer แบบไม่ซ้ำกันและเรียงลำดับ
 */
public class IntegerSet {
    private ArrayList<Integer> numbers;

    // Rep Invariant (RI):
    //  - numbers must not contain duplicate integers. (ห้ามมีเลขซ้ำ)
    //  - integers in numbers must be sorted in ascending order. (เลขต้องเรียงจากน้อยไปมาก)
    //  - numbers must not contain any null elements. (ห้ามมีค่า null)
    //
    // Abstraction Function (AF):
    //  AF(numbers) = เซตของจำนวนเต็มที่อยู่ใน ArrayList 'numbers'

    /**
     * Constructor เริ่มต้น สร้างเซตว่าง
     */
    public IntegerSet() {
        numbers = new ArrayList<>();
        checkRep();
    }

    /**
     * ตรวจสอบว่า Rep Invariant เป็นจริงหรือไม่
     */
    private void checkRep() {
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) == null || numbers.get(i+1) == null ||
                numbers.get(i) >= numbers.get(i+1)) {
                throw new RuntimeException("Rep invariant violated!");
            }
        }
    }

    /**
     * เพิ่มตัวเลขเข้าเซต
     * @param x ตัวเลขที่ต้องการเพิ่ม
     */
    public void add(Integer x) {
        if (numbers == null) {
             throw new RuntimeException("Cannot add null to the set.");
        }
        if (numbers.size() == 0) {
            numbers.add(x);
        } else {
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i).equals(x)) {
                    // ถ้ามีเลขซ้ำอยู่แล้ว ไม่ต้องเพิ่ม
                    return;
                }
                if (numbers.get(i) > x) {
                    numbers.add(i, x); // แทรก x ก่อนตัวที่มากกว่า
                    checkRep();
                    return;
                }
            }
            // ถ้า x มากกว่าทุกตัวในเซต ให้เพิ่มต่อท้าย
            numbers.add(x);
        }
        checkRep();
    }

    /**
     * ลบตัวเลขออกจากเซต
     * @param x ตัวเลขที่ต้องการลบ
     */
    public void remove(Integer x) {
        if (x == null) return;
        int index = numbers.indexOf(x);
        if (index != -1) { // ถ้าเจอ (index ไม่ใช่ -1)
            numbers.remove(index);
        }
        checkRep();
    }

    /**
     * ตรวจสอบว่ามีตัวเลขนี้อยู่ในเซตหรือไม่
     * @param x ตัวเลขที่ต้องการตรวจสอบ
     * @return true หากมี x อยู่ในเซต, false หากไม่มี
     */
    public boolean contains(Integer x) {
        if (x == null) return false;
        return numbers.indexOf(x) != -1;
    }

    /**
     * คืนค่าขนาดของเซต
     * @return จำนวนสมาชิกในเซต
     */
    public int size() {
        return numbers.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < numbers.size(); i++) {
            sb.append(numbers.get(i));
            if (i < numbers.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}