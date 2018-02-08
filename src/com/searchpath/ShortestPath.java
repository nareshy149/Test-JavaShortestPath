package com.searchpath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ShortestPath {

	public static void main(String[] args) {

		BufferedReader br = null;
		FileReader fr = null;

		try {
			File FILENAME = new File("src/resource/map.txt");

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;
			int column = 0;
			int row = 0;

			while ((sCurrentLine = br.readLine()) != null) {
				row++;
			}
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			while ((sCurrentLine = br.readLine()) != null) {
				for (int i = 0; i < sCurrentLine.length(); i++) {
					if (sCurrentLine.charAt(i) != ' ') {
						column++;
					}

				}

				break;
			}
			// System.out.println(column);
			// System.out.println(row);
			Character[][] mapChars = new Character[row][column];
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			int rowCounter = 0;
			int columnCounter = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				for (int i = 0; i < sCurrentLine.length(); i++) {
					if (sCurrentLine.charAt(i) != ' ') {
						mapChars[rowCounter][columnCounter] = sCurrentLine
								.charAt(i);
						columnCounter++;
					}

				}
				rowCounter++;
				columnCounter = 0;
			}

			for (int i = 0; i < mapChars.length; i++) {
				for (int j = 0; j < mapChars[i].length; j++) {
					System.out.print(mapChars[i][j]);
				}
				System.out.println();
			}

			Graph g = new Graph();
			Node[][] nodes = new Node[row][column];
			for (int i = 0; i < mapChars.length; i++) {
				for (int j = 0; j < mapChars[i].length; j++) {
					nodes[i][j] = new Node(mapChars[i][j]);
					if ((nodes[i][j]).label == 'S') {
						g.setRootNode(nodes[i][j]);
						(nodes[i][j]).label = '.';
					}
					if ((nodes[i][j]).label == 'E') {
						g.setDestinationNode(nodes[i][j]);
						(nodes[i][j]).label = '.';
					}
					g.addNode(nodes[i][j]);
				}

			}

			for (int i = 0; i < mapChars.length; i++) {
				for (int j = 0; j < mapChars[i].length; j++) {
					// System.out.print((nodes[i][j]).label);
					if ((nodes[i][j]).label == '.') {
						if ((i + 1) < mapChars.length
								&& (nodes[i + 1][j]).label == '.') {
							// System.out.println("char ");
							g.connectNode(nodes[i][j], nodes[i + 1][j]);
							// System.out.println("index" + (i + 1) + " " + j);
						}
						if ((j + 1) < mapChars[i].length
								&& (nodes[i][j + 1]).label == '.') {
							// System.out.println("char ");

							g.connectNode(nodes[i][j], nodes[i][j + 1]);
							// System.out.println("index" + i + " " + (j + 1));
						}

					}
				}
				System.out.println();
			}
			g.searchPath2(g.rootNode, g.destinationNode);

			for (int i = 0; i < mapChars.length; i++) {
				for (int j = 0; j < mapChars[i].length; j++) {
					System.out.print(nodes[i][j].label);
				}
				System.out.println();
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

}
