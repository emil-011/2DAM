package psp.unidad01.relacion103;

public class EchoCommandLineOptional {

	public static void main(String[] args) {
		
		String parametro1 = "parametro1";
		String parametro2 = "parametro2";
		
		if (args.length < 1) {
			System.out.println(parametro1);
			System.out.println(parametro2);
		} else if(args.length == 1) {
			System.out.println(args[0]);
			System.out.println(parametro2);	
		} else {
			for (String argumentos : args) {
				System.out.println(argumentos);
			}

		}
	}

}
