/*
You are a developer for a university. Your current project is to develop a system for students to find courses they share with friends. The university has a system for querying courses students are enrolled in, returned as a list of (ID, course) pairs.

Write a function that takes in a collection of (student ID number, course name) pairs and returns, for every pair of students, a collection of all courses they share.


Sample Input:

student_course_pairs_1 = [
  ["58", "Linear Algebra"],
  ["94", "Art History"],
  ["94", "Operating Systems"],
  ["17", "Software Design"],
  ["58", "Mechanics"],
  ["58", "Economics"],
  ["17", "Linear Algebra"],
  ["17", "Political Science"],
  ["94", "Economics"],
  ["25", "Economics"],
  ["58", "Software Design"],
]

Sample Output (pseudocode, in any order):

find_pairs(student_course_pairs_1) =>
{
  "58,17": ["Software Design", "Linear Algebra"]
  "58,94": ["Economics"]
  "58,25": ["Economics"]
  "94,25": ["Economics"]
  "17,94": []
  "17,25": []
}

Additional test cases:

Sample Input:

student_course_pairs_2 = [
  ["0", "Advanced Mechanics"],
  ["0", "Art History"],
  ["1", "Course 1"],
  ["1", "Course 2"],
  ["2", "Computer Architecture"],
  ["3", "Course 1"],
  ["3", "Course 2"],
  ["4", "Algorithms"]
]

Sample output:

find_pairs(student_course_pairs_2) =>
{
  "1,0":[]
  "2,0":[]
  "2,1":[]
  "3,0":[]
  "3,1":["Course 1", "Course 2"]
  "3,2":[]
  "4,0":[]
  "4,1":[]
  "4,2":[]
  "4,3":[]
} 

Sample Input:
student_course_pairs_3 = [
  ["23", "Software Design"], 
  ["3", "Advanced Mechanics"],
  ["2", "Art History"], 
  ["33", "Another"],
]

Sample output:

find_pairs(student_course_pairs_3) =>
{
  "23,3": []
  "23,2": []
  "23,33":[]
  "3,2":  []
  "3,33": []
  "2,33": []
}

n: number of student,course pairs in the input
s: number of students
c: total number of courses being offered (note: The number of courses any student can take is bounded by a small constant)

*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class StudentCoursePairs {
    public static void main(String[] argv) {
        String[][] studentCoursePairs1 = {
                {"58", "Linear Algebra"},
                {"94", "Art History"},
                {"94", "Operating Systems"},
                {"17", "Software Design"},
                {"58", "Mechanics"},
                {"58", "Economics"},
                {"17", "Linear Algebra"},
                {"17", "Political Science"},
                {"94", "Economics"},
                {"25", "Economics"},
                {"58", "Software Design"}
        };

        String[][] studentCoursePairs2 = {
                {"0", "Advanced Mechanics"},
                {"0", "Art History"},
                {"1", "Course 1"},
                {"1", "Course 2"},
                {"2", "Computer Architecture"},
                {"3", "Course 1"},
                {"3", "Course 2"},
                {"4", "Algorithms"}
        };

        String[][] studentCoursePairs3 = {
                {"23", "Software Design"},
                {"3",  "Advanced Mechanics"},
                {"2",  "Art History"},
                {"33", "Another"},
        };

        Map<String, Set<String>> studentCourseMap = new HashMap<>();
        createStudentCourseMap(studentCoursePairs3, studentCourseMap);
        Map<String,Set<String>> resultMap = new HashMap<>();
        generatePairsOfStudent(studentCourseMap, resultMap);

        printMap(resultMap);
    }

    public static void printMap( Map<String, Set<String>> map) {
        for(Map.Entry<String, Set<String>> e: map.entrySet()) {
            System.out.println(e.getKey() + ":" + e.getValue());
        }
    }

    public static void createStudentCourseMap(String[][] studentCourseList, Map<String, Set<String>> studentCourseMap) {
        if(studentCourseList.length < 1)
            return;

        for(String[] pair: studentCourseList) {
            Set<String> courses = studentCourseMap.getOrDefault(pair[0], new HashSet<>());
            courses.add(pair[1]);
            studentCourseMap.put(pair[0],courses);
        }
    }

    public static void generatePairsOfStudent(Map<String, Set<String>> studentCourseMap, Map<String, Set<String>> resultMap) {
        if(studentCourseMap.size() < 1)
            return;

        List<String> keysList = new ArrayList<>();
        keysList.addAll(studentCourseMap.keySet());

        for(int i=0; i<keysList.size()-1; i++) {
            for(int j=i+1; j<keysList.size(); j++) {
                List<String> list1 = new ArrayList<>(studentCourseMap.get(keysList.get(i)));
                List<String> list2 = new ArrayList<>(studentCourseMap.get(keysList.get(j)));
                Set<String> intersection = list1.stream().distinct().filter(list2::contains).collect(Collectors.toSet());
                resultMap.put(keysList.get(i) +", " + keysList.get(j), intersection);
            }

        }
    }
}
