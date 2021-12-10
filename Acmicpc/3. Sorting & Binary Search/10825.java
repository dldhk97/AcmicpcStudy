import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Student implements Comparable<Student>{
    private String name;
    private int koreanScore;
    private int englishScore;
    private int mathScore;

    public Student(String name, int koreanScore, int englishScore, int mathScore){
        this.name = name;
        this.koreanScore = koreanScore;
        this.englishScore = englishScore;
        this.mathScore = mathScore;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Student s){

        if(this.koreanScore != s.koreanScore) return s.koreanScore - this.koreanScore;
        if(this.englishScore != s.englishScore) return this.englishScore - s.englishScore;
        if(this.mathScore != s.mathScore) return s.mathScore - this.mathScore;
        return this.name.compareTo(s.name);
    }
}


public class Main{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int studentCnt = Integer.parseInt(br.readLine());

        Student[] students = new Student[studentCnt];
        for(int i = 0 ; i < studentCnt ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            int koreanScore = Integer.parseInt(st.nextToken());
            int englishScore = Integer.parseInt(st.nextToken());
            int mathScore = Integer.parseInt(st.nextToken());

            students[i] = new Student(name, koreanScore, englishScore, mathScore);
        }

        Arrays.sort(students);

        StringBuilder sb = new StringBuilder();
        for(Student s : students){
            sb.append(s.getName());
            sb.append('\n');
        }

        System.out.println(sb.toString());
    }
}