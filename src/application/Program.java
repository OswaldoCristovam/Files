package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		List<Product> list = new ArrayList<>();
		
		System.out.println("Enter a file path: ");
		String strPath = sc.nextLine();
		
		File file = new File(strPath);
		
		String sourceFile = file.getParent();
		
		boolean succes = new File(sourceFile + "\\out").mkdir();
		
		String targetFile = sourceFile + "\\out\\summary.csv";
		
		try (BufferedReader br = new BufferedReader(new FileReader(strPath))){
			String line = br.readLine();
			while (line != null) {
				String[] parts = line.split(",");
				
				String name = parts[0];
				double price = Double.parseDouble(parts[1]);
				int quantity = Integer.parseInt(parts[2]);
				list.add(new Product(name, price, quantity));
				line = br.readLine();
			}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))){
				for (Product product : list) {
					bw.write(product.getName() + ", " + String.format("%.2f", product.total()));
					bw.newLine();
				}
				System.out.println(targetFile + " CREATED");
			}
			catch (IOException e) {
				System.out.println("ERROR Write file: " + e.getMessage());
			}
			
		}
		catch (IOException e) {
			System.out.println("ERROR Reade file: " + e.getMessage());
		}
		
		sc.close();
	}

}
