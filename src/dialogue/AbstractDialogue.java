<<<<<<< HEAD
package dialogue;

import java.io.File;

import com.golden.gamedev.util.FileUtil;

public abstract class AbstractDialogue {
	
	public abstract void goToNextLine(DialogueObject choice);
	
	public abstract String getCurrentLine();
	
	public abstract boolean isDone();
	
	String[] readFile(String url){
		String[] lines =  FileUtil.fileRead(new File(url));
		return lines;
	}
	
	public abstract class DialogueObject{
		
	}

}


=======
package dialogue;

import java.io.File;

import com.golden.gamedev.util.FileUtil;

public abstract class AbstractDialogue {
	
	/**
	 * the current line within the Dialogue object changes based on the choice given
	 * @param choice
	 */
	public abstract void goToNextLine(DialogueObject choice);
	
	/**
	 * returns the current line in the Dialogue object without modifying or moving on
	 * @return
	 */
	public abstract String getCurrentLine();
	
	/**
	 * returns true if the current lines is the last available line in the script
	 * @return
	 */
	public abstract boolean isDone();
	
	String[] readFile(String url){
		String[] lines =  FileUtil.fileRead(new File(url));
		return lines;
	}

	/**
	 * wraps all of the possible ways to make a choice between dialogue options
	 * @author rachel
	 *
	 */
	public abstract class DialogueObject{
		
	}

}


>>>>>>> ec276a1f7a4dd3ee4cb696bfad1b456c177ee0fe
