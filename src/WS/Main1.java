/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS;

/**
 *
 * @author nvqua
 */
//[Mã câu hỏi (qCode): 26xQW6i0].  Một dịch vụ web (hỗ trợ SOAP version 1.1) được định nghĩa và mô tả trong tệp CharacterService.wsdl, được triển khai trên server tại URL http://<Exam_IP>:8080/JNPWS/CharacterService?wsdl để xử lý các bài toán về chuỗi và ký tự.
//Yêu cầu: Viết chương trình tại máy trạm (WS client) để giao tiếp với CharacterService thực hiện các công việc sau:
//a. Triệu gọi phương thức requestStringArray với tham số đầu vào là mã sinh viên (studentCode) và mã câu hỏi (qCode) để nhận về một danh sách chuỗi (List<String>) từ server.
//b. Phân loại các từ trong mảng chuỗi thành các nhóm có cùng số lượng nguyên âm. Tạo một chuỗi cho mỗi nhóm, trong đó liệt kê các từ cách nhau bằng dấu phẩy, và sắp xếp các từ theo thứ tự từ điển trong mỗi nhóm.
//c. Triệu gọi phương thức submitCharacterStringArray(String studentCode, String qCode, List<String> data) để gửi danh sách chuỗi kết quả trở lại server, trong đó mỗi phần tử là một nhóm từ với cùng số lượng nguyên âm.
//Ví dụ: Nếu danh sách chuỗi nhận được từ phương thức requestCharacter là ["apple", "banana", "pear", "grape", "kiwi"], các nhóm có thể là:
//•	Nhóm 2 nguyên âm: "apple, banana"
//•	Nhóm 1 nguyên âm: "grape, kiwi, pear"
//Danh sách kết quả sẽ là ["apple, banana", "grape, kiwi, pear"], và danh sách này sẽ được gửi lại server qua phương thức submitCharacter.
//d. Kết thúc chương trình client.
// ueuoai
import java.io.*;
import WS_List.*;
import java.util.*;

public class Main1 {

    public static void main(String[] args) throws Exception {
        CharacterService_Service sv = new CharacterService_Service();
        CharacterService characterService = sv.getCharacterServicePort();
        String studentCode = "B22DCCN650";
        String qCode = "26xQW6i0";
        List<String> l = characterService.requestStringArray(studentCode, qCode);
        System.out.println(l);

        // Định nghĩa tập nguyên âm (cả hoa và thường)
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

// Bản đồ: key = số nguyên âm, value = danh sách các từ có cùng số nguyên âm
        TreeMap<Integer, List<String>> groups = new TreeMap<>();

// Duyệt qua từng từ trong danh sách
        for (String word : l) {
            int count = 0;
            for (char c : word.toCharArray()) {
                if (vowels.contains(c)) {
                    count++;
                }
            }
            // Thêm từ vào nhóm tương ứng
            if(groups.containsKey(count)){
                groups.get(count).add(word);
            }
            else{
                List<String> l1 = new ArrayList<>(Arrays.asList(word));
                groups.put(count, l1);
            }
        }

        List<String> result = new ArrayList<>();
        
        
        groups.forEach((t, u) -> {
            System.out.println(t);
            Collections.sort(u);
            u.forEach((t1) -> {
                result.add(t1);
            });
        });
        System.out.println(result);
        characterService.submitCharacterStringArray(studentCode, qCode, result);

    }
}
