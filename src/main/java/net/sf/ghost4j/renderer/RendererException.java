/*
 * Ghost4J: a Java wrapper for Ghostscript API.
 *
 * Distributable under LGPL license.
 * See terms of license at http://www.gnu.org/licenses/lgpl.html.
 */

package net.sf.ghost4j.renderer;

/**
 * Class representing a renderer exception.
 * This exception may be thrown while rendering a document with a Renderer subclass.
 * @author Gilles Grousset (gi.grousset@gmail.com)
 */
public class RendererException extends Exception {

	public RendererException() {
		super();
	}

	public RendererException(String message) {
		super(message);
	}

	public RendererException(Throwable cause) {
		super(cause);
	}

	public RendererException(String message, Throwable cause) {
		super(message, cause);
	}
}
