//Part: 1
// Written by: Patrick Keenan 40175307
// -----------------------------------
package Assignment_4;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p>Author: Patrick Keenan, Student ID: 40175307 </p>
 * <p>COMP 249 </p>
 * <p>Assignment 4 </p>
 * <p>Due Date: April 24 2021 </p>
 * <p>File_Parser Class which contains only a main method</p>
 */
public class File_Parser {
	/**
	 * Main method demands the user for a file to parse, and returns three files: vowel_verbiage.txt, obsessive_o.txt, and distinct_data.txt
	 * These files contain the words in the file with more than 3 vowels, those that start with an o, and one copy of every unique word.
	 * @param args
	 */
	public static void main(String[] args){
		Scanner user = new Scanner(System.in);
		Scanner sc = null;
		ArrayList<String> words = new ArrayList(); 
		ArrayList<String> vowels = new ArrayList(); //words with more than three values in them
		ArrayList<String> obsessive_o = new ArrayList(); //words that start with an o
		ArrayList<String> distinct = new ArrayList(); //distinct word
		System.out.println("Enter the name of the text file you wish to parse: ");
		String input = user.next();
		try {
			sc = new Scanner(new FileInputStream(input));
		}
		catch (FileNotFoundException e) {
			System.out.println("The file " +input+" could not be found.");
			System.exit(0);
		}
		while (sc.hasNext()) { //separate every word in the file and put them into an arraylist
			String[] word_splitter = null;
			String line =""; 
			line = sc.nextLine();
			word_splitter = line.split(" ");
			for (int i=0; i<word_splitter.length; i++) {
				String betterline = word_splitter[i].replaceAll("[^a-zA-Z0-9]", "");		
				words.add(betterline);
			}		
		}
		
		for (int i=0; i<words.size(); i++) {
			String currentword = words.get(i);
			char[] currentchars = currentword.toCharArray();
			int vowelcount=0;
			for (int j=0; j<currentchars.length; j++) {
				if(currentchars[j]==('a')||currentchars[j]==('e')||currentchars[j]==('i')||currentchars[j]==('o')||currentchars[j]==('u') ||
						currentchars[j]==('A')||currentchars[j]==('E')||currentchars[j]==('I')||currentchars[j]==('O')||currentchars[j]==('U')) {
					vowelcount++;
				}
			}
			if (vowelcount>3) {
				vowels.add(currentword);
			}
			if (currentword.startsWith("o")||currentword.startsWith("O")) {
				obsessive_o.add(currentword);
			}
			for(int j=0; j<words.size();j++) {
				boolean dist = true;
				if(distinct.contains(currentword)) {
				 dist=false;
				}
				if(dist) {
					distinct.add(currentword);
				}			
			}
		}
		try{
			PrintWriter pw_v = new PrintWriter(new FileOutputStream("vowel_verbiage.txt"));
			PrintWriter pw_o = new PrintWriter(new FileOutputStream("obsessive_o.txt"));
			PrintWriter pw_d = new PrintWriter(new FileOutputStream("distinct_data.txt"));		
			pw_v.println("Word Count: "+vowels.size());
			pw_o.println("Word Count: "+obsessive_o.size());
			pw_d.println("Word Count: "+distinct.size());
			for (int i=0; i<vowels.size(); i++) {
				pw_v.println(vowels.get(i));
			}
			for (int i=0; i<obsessive_o.size(); i++) {
				pw_o.println(obsessive_o.get(i));	
			}
			for (int i=0; i<distinct.size(); i++) {
				pw_d.println(distinct.get(i));
			}
			pw_v.close();
			pw_o.close();
			pw_d.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Could not create outputfile");
			System.exit(0);
		}

	}

	
}
