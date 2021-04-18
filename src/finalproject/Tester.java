package finalproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

// Student tests
// ==========================================================================================

class veryEasy3x3_solution implements Runnable {

	@Override
	public void run() {
		String localDir = System.getProperty("user.dir");
		InputStream in = null;
		try {
			in = new FileInputStream(
					localDir + "/src/finalproject/Sudokus/veryEasy3x3.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int puzzleSize = 0;
		try {
			puzzleSize = ChessSudoku.readInteger(in);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ChessSudoku s = new ChessSudoku(puzzleSize);

		// You can modify these to add rules to your sudoku
		s.knightRule = false;
		s.kingRule = false;
		s.queenRule = false;

		// read the rest of the Sudoku puzzle
		try {
			s.read(in);
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean allSolutions = false;
		s.solve(allSolutions);

		// Test if the final Puzzle is the same
		int[][] desiredResult = {{3, 6, 1, 8, 9, 5, 4, 7, 2},
				{9, 5, 2, 7, 3, 4, 8, 1, 6}, {7, 4, 8, 1, 6, 2, 3, 9, 5},
				{5, 7, 3, 9, 2, 6, 1, 4, 8}, {8, 2, 9, 5, 4, 1, 6, 3, 7},
				{4, 1, 6, 3, 8, 7, 5, 2, 9}, {6, 9, 7, 4, 1, 8, 2, 5, 3},
				{1, 8, 5, 2, 7, 3, 9, 6, 4}, {2, 3, 4, 6, 5, 9, 7, 8, 1}};

		for (int i = 0; i < 9; i++) {
			// System.out.println(Arrays.toString(desiredResult[i]));
			// System.out.println(Arrays.toString(s.grid[i]));
			for (int j = 0; j < 9; j++) {
				if (desiredResult[i][j] != s.grid[i][j]) {
					throw new AssertionError("Test failed.");
				}
			}
		}
		System.out.println("Test easy3x3 passed.");
	}
}

class easy3x3_solution implements Runnable {

	@Override
	public void run() {
		String localDir = System.getProperty("user.dir");
		InputStream in = null;
		try {
			in = new FileInputStream(
					localDir + "/src/finalproject/Sudokus/easy3x3.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int puzzleSize = 0;
		try {
			puzzleSize = ChessSudoku.readInteger(in);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ChessSudoku s = new ChessSudoku(puzzleSize);

		// You can modify these to add rules to your sudoku
		s.knightRule = false;
		s.kingRule = false;
		s.queenRule = false;

		// read the rest of the Sudoku puzzle
		try {
			s.read(in);
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean allSolutions = false;
		s.solve(allSolutions);

		// Test if the final Puzzle is the same
		int[][] desiredResult = {{4, 5, 1, 2, 9, 6, 8, 3, 7},
				{7, 8, 2, 5, 4, 3, 6, 9, 1}, {3, 9, 6, 8, 1, 7, 4, 5, 2},
				{9, 1, 3, 7, 8, 5, 2, 4, 6}, {6, 4, 7, 1, 2, 9, 3, 8, 5},
				{8, 2, 5, 6, 3, 4, 7, 1, 9}, {1, 6, 4, 9, 7, 8, 5, 2, 3},
				{2, 7, 8, 3, 5, 1, 9, 6, 4}, {5, 3, 9, 4, 6, 2, 1, 7, 8}};

		for (int i = 0; i < 9; i++) {
			// System.out.println(Arrays.toString(desiredResult[i]));
			// System.out.println(Arrays.toString(s.grid[i]));
			for (int j = 0; j < 9; j++) {
				if (desiredResult[i][j] != s.grid[i][j]) {
					throw new AssertionError("Test failed.");
				}
			}
		}
		System.out.println("Test easy3x3 passed.");
	}
}

class medium3x3_12solutions_solution implements Runnable {

	@Override
	public void run() {
		String localDir = System.getProperty("user.dir");
		InputStream in = null;
		try {
			in = new FileInputStream(localDir
					+ "/src/finalproject/Sudokus/medium3x3_twelveSolutions.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int puzzleSize = 0;
		try {
			puzzleSize = ChessSudoku.readInteger(in);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ChessSudoku s = new ChessSudoku(puzzleSize);

		// You can modify these to add rules to your sudoku
		s.knightRule = false;
		s.kingRule = false;
		s.queenRule = false;

		// read the rest of the Sudoku puzzle
		try {
			s.read(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean allSolutions = true;
		s.solve(allSolutions);

		// Test if the final Puzzle is the same
		int counter = 0;
		for (ChessSudoku cur : s.solutions) {
			counter++;
		}
		if (counter != 12) {
			throw new AssertionError(
					"Test failed.There should be 12 solutions");
		}
		System.out.println("Test medium3x3_12solutions_solution passed.");
	}
}

/*
 * Checks that every non-private method in ChessSudoku is one of the required
 * methods
 */
class ChessSudoku_extra_methods implements Runnable {
	@Override
	public void run() {
		Class<ChessSudoku> cls = ChessSudoku.class;
		TMethod[] requiredMethods = getRequiredMethods();

		for (Method m : cls.getDeclaredMethods()) {
			if (!Modifier.isPrivate(m.getModifiers())
					&& !TMethod.elementOf(m, requiredMethods)) {
				throw new AssertionError("Extra method found: " + m);
			}
		}
	}

	private TMethod[] getRequiredMethods() {
		TMethod[] requiredMethods = new TMethod[7];
		requiredMethods[0] = new TMethod(Modifier.PUBLIC, Void.TYPE, "solve",
				new Class[]{boolean.class}, new Class[0]);
		requiredMethods[1] = new TMethod(Modifier.STATIC, int.class,
				"readInteger", new Class[]{InputStream.class},
				new Class[]{Exception.class});
		requiredMethods[2] = new TMethod(Modifier.STATIC, String.class,
				"readWord", new Class[]{InputStream.class},
				new Class[]{Exception.class});
		requiredMethods[3] = new TMethod(Modifier.PUBLIC, Void.TYPE, "read",
				new Class[]{InputStream.class}, new Class[]{Exception.class});
		requiredMethods[4] = new TMethod(0, Void.TYPE, "printFixedWidth",
				new Class[]{String.class, int.class}, new Class[0]);
		requiredMethods[5] = new TMethod(Modifier.PUBLIC, Void.TYPE, "print",
				new Class[0], new Class[0]);
		requiredMethods[6] = new TMethod(Modifier.PUBLIC + Modifier.STATIC,
				Void.TYPE, "main", new Class[]{String[].class},
				new Class[]{Exception.class});
		return requiredMethods;
	}
}

/*
 * Checks that every non-private field in ChesSudoku is one of the required
 * fields
 */
class ChessSudoku_extra_fields implements Runnable {
	@Override
	public void run() {
		Class<ChessSudoku> cls = ChessSudoku.class;
		TField[] requiredFields = getRequiredFields();

		for (Field f : cls.getDeclaredFields()) {
			if (!Modifier.isPrivate(f.getModifiers())
					&& !TField.elementOf(f, requiredFields))
				throw new AssertionError("Extra field found: " + f);
		}
	}

	private TField[] getRequiredFields() {
		TField[] requiredFields = new TField[7];
		requiredFields[0] = new TField(Modifier.PUBLIC, int.class, "SIZE");
		requiredFields[1] = new TField(Modifier.PUBLIC, int.class, "N");
		requiredFields[2] = new TField(Modifier.PUBLIC, int[][].class, "grid");
		requiredFields[3] = new TField(Modifier.PUBLIC, boolean.class,
				"knightRule");
		requiredFields[4] = new TField(Modifier.PUBLIC, boolean.class,
				"kingRule");
		requiredFields[5] = new TField(Modifier.PUBLIC, boolean.class,
				"queenRule");
		requiredFields[6] = new TField(Modifier.PUBLIC, HashSet.class,
				"solutions");
		return requiredFields;
	}
}

/*
 * Checks that every non-private constructor in ChessSudoku is one of the
 * required constructors
 */
class ChessSudoku_extra_constructors implements Runnable {
	@Override
	@SuppressWarnings("rawtypes")
	public void run() {
		Class<ChessSudoku> cls = ChessSudoku.class;
		TConstructor[] requiredConstructors = getRequiredConstructors();

		for (Constructor c : cls.getDeclaredConstructors()) {
			if (!Modifier.isPrivate(c.getModifiers())
					&& !TConstructor.elementOf(c, requiredConstructors))
				throw new AssertionError("Extra constructor found: " + c);
		}
	}

	public TConstructor[] getRequiredConstructors() {
		TConstructor[] requiredConstructors = new TConstructor[1];
		// Get rid of "class " at beginning of name
		String name = ChessSudoku.class.toString().split(" ")[1];

		requiredConstructors[0] = new TConstructor(Modifier.PUBLIC, name,
				new Class[]{int.class}, new Class[0]);
		return requiredConstructors;
	}
}

/*
 * Checks that every non-private nested class in ChessSudoku is one of the
 * required nested classes
 */
@SuppressWarnings("rawtypes")
class ChessSudoku_extra_classes implements Runnable {
	@Override
	public void run() {
		Class<ChessSudoku> cls = ChessSudoku.class;
		Class[] requiredClasses = getRequiredClasses();

		for (Class c : cls.getDeclaredClasses()) {
			if (!Arrays.asList(requiredClasses).contains(c))
				throw new AssertionError("Extra nested class found: " + c);
		}
	}

	public Class[] getRequiredClasses() {
		Class[] requiredClasses = new Class[0];
		return requiredClasses;
	}
}

class Illegal_helper_code implements Runnable {
	private static String[] tests = {"finalproject.ChessSudoku_extra_methods",
			"finalproject.ChessSudoku_extra_fields",
			"finalproject.ChessSudoku_extra_constructors",
			"finalproject.ChessSudoku_extra_classes"};

	@Override
	public void run() {
		for (String str : tests) {
			try {
				Runnable testCase = (Runnable) Class.forName(str)
						.getDeclaredConstructor().newInstance();
				testCase.run();
			} catch (InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				e.printStackTrace();
				throw new AssertionError(
						"An unexpected error occurred and the test " + str
								+ " could not be run.");
			}
		}

		System.out.println("Test passed.");
	}
}

// Utility classes
// ==========================================================================================

/*
 * Stores information about methods. Is meant to be compared to instances of
 * java.lang.reflect.Method (which has no public constructor).
 */
@SuppressWarnings("rawtypes")
class TMethod {
	private int modifiers;
	private Class returnType;
	private String name;
	private Class[] params;
	private Class[] exceptions;

	/*
	 * Creates a new TMethod by saving all the given arguments directly to the
	 * corresponding fields
	 */
	public TMethod(int modifiers, Class returnType, String name, Class[] params,
			Class[] exceptions) {
		this.modifiers = modifiers;
		this.returnType = returnType;
		this.name = name;
		this.params = params;
		this.exceptions = exceptions;
	}

	/*
	 * A TMethod is equal to a TMethod or a Method if and only if all its fields
	 * match
	 *
	 * This operation is not commutative for TMethods and Methods
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Method) {
			Method m = (Method) o;
			return this.modifiers == m.getModifiers()
					&& this.returnType.equals(m.getReturnType())
					&& this.name.equals(m.getName())
					&& Arrays.equals(this.params, m.getParameterTypes())
					&& Arrays.equals(this.exceptions, m.getExceptionTypes());
		} else if (o instanceof TMethod) {
			TMethod t = (TMethod) o;
			return this.modifiers == t.modifiers
					&& this.returnType.equals(t.returnType)
					&& this.name.equals(t.name)
					&& Arrays.equals(this.params, t.params)
					&& Arrays.equals(this.exceptions, t.exceptions);
		} else
			return false;
	}

	/*
	 * Checks if method is equal (using TMethod.equals(method)) to any of the
	 * elements in tMethods
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static boolean elementOf(Method method, TMethod[] tMethods) {
		for (TMethod t : tMethods) {
			if (t.equals(method))
				return true;
		}
		return false;
	}
}

/*
 * Stores information about Fields. Is meant to be compared to instances of
 * java.lang.reflect.Field (which has no public constructor).
 */
@SuppressWarnings("rawtypes")
class TField {
	private int modifiers;
	private Class type;
	private String name;

	/*
	 * Creates a new TField by saving all the given arguments directly to the
	 * corresponding fields
	 */
	public TField(int modifiers, Class type, String name) {
		this.modifiers = modifiers;
		this.type = type;
		this.name = name;
	}

	/*
	 * A TField is equal to a TField or a Field if and only if all its fields
	 * match
	 *
	 * This operation is not commutative for TFields and Fields
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Field) {
			Field f = (Field) o;
			return this.modifiers == f.getModifiers()
					&& this.type.equals(f.getType())
					&& this.name.equals(f.getName());
		} else if (o instanceof TField) {
			TField t = (TField) o;
			return this.modifiers == t.modifiers && this.type.equals(t.type)
					&& this.name.equals(t.name);
		} else
			return false;
	}

	/*
	 * Checks if field is equal (using TField.equals(field)) to any of the
	 * elements in tFields
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static boolean elementOf(Field field, TField[] tFields) {
		for (TField t : tFields) {
			if (t.equals(field))
				return true;
		}
		return false;
	}
}

/*
 * Stores information about Constructors. Is meant to be compared to instances
 * of java.lang.reflect.Constructor (which has no public constructor*).
 *
 * * "Ironic. He could constructor others, but not himself."
 */
@SuppressWarnings("rawtypes")
class TConstructor {
	private int modifiers;
	private String name;
	private Class[] params;
	private Class[] exceptions;

	/*
	 * Creates a new TMethod by saving all the given arguments directly to the
	 * corresponding fields
	 */
	public TConstructor(int modifiers, String name, Class[] params,
			Class[] exceptions) {
		this.modifiers = modifiers;
		this.name = name;
		this.params = params;
		this.exceptions = exceptions;
	}

	/*
	 * A TConstructor is equal to a TConstructor or a Constructor if and only if
	 * all its fields match
	 *
	 * This operation is not commutative for TConstructors and Constructors
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Constructor) {
			Constructor c = (Constructor) o;
			return this.modifiers == c.getModifiers()
					&& this.name.equals(c.getName())
					&& Arrays.equals(this.params, c.getParameterTypes())
					&& Arrays.equals(this.exceptions, c.getExceptionTypes());
		} else if (o instanceof TConstructor) {
			TConstructor t = (TConstructor) o;
			return this.modifiers == t.modifiers && this.name.equals(t.name)
					&& Arrays.equals(this.params, t.params)
					&& Arrays.equals(this.exceptions, t.exceptions);
		} else
			return false;
	}

	/*
	 * Checks if constructor is equal (using TConstructor.equals(constructor))
	 * to any of the elements in tConstructors
	 */
	@SuppressWarnings("unlikely-arg-type")
	public static boolean elementOf(Constructor constructor,
			TConstructor[] tConstructors) {
		for (TConstructor t : tConstructors) {
			if (t.equals(constructor))
				return true;
		}
		return false;
	}
}

// Main class
// ================================================================================

public class Tester {
	// To skip running some tests, just comment them out below.
	static String[] tests = {
			"finalproject.Illegal_helper_code",
			"finalproject.veryEasy3x3_solution",
			"finalproject.easy3x3_solution",
			"finalproject.medium3x3_12solutions_solution"
	};

	public static void main(String[] args) {
		int numPassed = 0;
		ArrayList<String> failedTests = new ArrayList<String>(tests.length);
		for (String className : tests) {
			System.out.printf("%n======= %s =======%n", className);
			System.out.flush();
			try {
				Runnable testCase = (Runnable) Class.forName(className)
						.getDeclaredConstructor().newInstance();
				testCase.run();
				numPassed++;
			} catch (AssertionError e) {
				System.out.println(e);
				failedTests.add(className);
			} catch (StackOverflowError e) {
				StackTraceElement[] elements = e.getStackTrace();
				System.out.println(className + " caused a stack overflow at: ");
				for (int i = 0; i < 5 && i < elements.length; i++) {
					System.out.println(elements[i]);
				}
				if (elements.length >= 5) {
					System.out.println("...and " + (elements.length - 5)
							+ " more elements.");
				}
				failedTests.add(className);
			} catch (Throwable t) {
				t.printStackTrace();
				failedTests.add(className);
			}
		}
		System.out.printf("%n%n%d of %d tests passed.%n", numPassed,
				tests.length);
		if (failedTests.size() > 0) {
			System.out.println("Failed test(s):");
			for (String className : failedTests) {
				int dotIndex = className.indexOf('.');
				System.out.println("  " + className.substring(dotIndex + 1));
			}
		}
		if (numPassed == tests.length) {
			System.out.println("All clear! Great work :)");
		}
	}

	public static boolean isSolved(ChessSudoku puzzle, boolean knightRule,
			boolean kingRule, boolean queenRule) {
		for (int row = 0; row < puzzle.N; row++) {
			for (int col = 0; col < puzzle.N; col++) {
				boolean isValidValue = isValidValue(puzzle, row, col,
						knightRule, kingRule, queenRule);
				if (!isValidValue)
					return false;
			}
		}

		return true;
	}

	public static boolean isValidValue(ChessSudoku puzzle, int row, int col,
			boolean knightRule, boolean kingRule, boolean queenRule) {
		int currentValue = puzzle.grid[row][col];

		// Invalid value / puzzle not filled
		if (currentValue <= 0 || currentValue > puzzle.N)
			return false;

		// Check block
		int firstRowInBlock = puzzle.SIZE * (row / puzzle.SIZE);
		int firstColInBlock = puzzle.SIZE * (col / puzzle.SIZE);
		for (int r = firstRowInBlock; r < firstRowInBlock + puzzle.SIZE; r++) {
			for (int c = firstColInBlock; c < firstColInBlock
					+ puzzle.SIZE; c++) {
				if (r == row && c == col)
					continue;
				else if (puzzle.grid[r][c] == currentValue)
					return false;
			}
		}

		// Check row
		for (int c = 0; c < puzzle.N; c++) {
			if (c == col)
				continue;
			else if (puzzle.grid[row][c] == currentValue)
				return false;
		}

		// Check column
		for (int r = 0; r < puzzle.N; r++) {
			if (r == row)
				continue;
			else if (puzzle.grid[r][col] == currentValue)
				return false;
		}

		// Check knight rule
		if (knightRule) {
			for (int vertJump : new int[]{-2, -1, 1, 2}) {
				int r = row + vertJump;
				if (r < 0 || r == row)
					continue;
				if (r >= puzzle.N)
					break;

				int horizJump = Math.abs(vertJump) == 2 ? -1 : -2;
				int c = col + horizJump;
				if (c >= puzzle.N)
					continue;
				else if (c >= 0 && puzzle.grid[r][c] == currentValue)
					return false;

				c = col - horizJump;
				if (c >= 0 && c < puzzle.N && puzzle.grid[r][c] == currentValue)
					return false;

			}
		}

		// Check king rule
		if (kingRule) {
			for (int r = row - 1; r <= row + 1; r += 2) {
				if (r < 0)
					continue;
				if (r >= puzzle.N)
					break;
				for (int c = col - 1; c <= col + 1; c += 2) {
					if (c < 0)
						continue;
					if (c >= puzzle.N)
						break;
					if (puzzle.grid[r][c] == currentValue)
						return false;
				}
			}
		}

		// Check queen rule
		if (queenRule) {
			int diff = col - row;
			// Right down diagonal
			for (int r = 0; r < puzzle.N; r++) {
				int c = r + diff;
				if (c < 0)
					continue;
				if (c >= puzzle.N)
					break;
				if (puzzle.grid[r][c] == currentValue)
					return false;
			}
			// Right up diagonal
			int sum = row + col;
			for (int r = 0; r < puzzle.N; r++) {
				int c = sum - r;
				if (c < 0)
					break;
				if (c >= puzzle.N)
					continue;
				if (puzzle.grid[r][c] == currentValue)
					return false;
			}
		}

		return true;
	}
}
