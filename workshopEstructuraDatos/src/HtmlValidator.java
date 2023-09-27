import java.util.Queue;
import java.util.Stack;

public class HtmlValidator {

	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		Stack<HtmlTag> stack = new Stack<>();

		// Recorre todas las etiquetas en la cola una por una
		for (HtmlTag tag : tags) {
			if (tag.isSelfClosing()) {
				// Si la etiqueta es de cierre automático, ignórala y pasa a la siguiente
				continue;
			}

			if (tag.isOpenTag()) {
				// Si la etiqueta es de apertura, agrégala a una pila
				stack.push(tag);
			} else {
				// Si la etiqueta es de cierre, comprueba si coincide con la etiqueta de apertura más reciente
				if (stack.isEmpty()) {
					return null; // No hay etiqueta de apertura correspondiente, el HTML no es válido
				}

				if (stack.peek().matches(tag)) {
					stack.pop(); // La etiqueta de cierre coincide con la etiqueta de apertura, retírala de la pila
				} else {
					return stack; // La etiqueta de cierre no coincide con la etiqueta de apertura, el HTML no es válido
				}
			}
		}

		// Al final, si la pila está vacía, significa que todas las etiquetas se cerraron correctamente
		// Si la pila no está vacía, significa que algunas etiquetas no se cerraron adecuadamente
		return stack;
	}
}


