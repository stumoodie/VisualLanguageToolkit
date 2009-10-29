/*
Copyright 2009, Court of the University of Edinburgh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/

package org.pathwayeditor.figure.figuredefn.rendering;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

/**
 * @author smoodie
 *
 */
public class SVGImageFactory {
	private File imgFile;
	
	public SVGImageFactory() {
	}
	
	
	public void createImage(InputStream svgStream, double size) throws IOException{
		// Get a DOMImplementation.
		OutputStream os = null;
		try {
			PNGTranscoder t = new PNGTranscoder();

			// Create the transcoder input.
			TranscoderInput input = new TranscoderInput(svgStream);
			this.imgFile = File.createTempFile("img", "png");
			imgFile.deleteOnExit();
			// Create the transcoder output.
			os = new FileOutputStream(imgFile);
			TranscoderOutput output = new TranscoderOutput(os);

			// Save the image.
			t.transcode(input, output);
		} catch (TranscoderException ex) {
			throw new IOException("Error generating image format: "
					+ ex.getMessage());
		} finally {
			if (os != null) {
				// Flush and close the stream.
				os.flush();
				os.close();
			}
		}
	}
	
	public InputStream getPNGImageAsStream() throws IOException{
		if(this.imgFile == null) throw new IllegalStateException("Image not created");
		
		InputStream retVal = new FileInputStream(this.imgFile);
		return retVal;
	}
	
}
