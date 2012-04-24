package dialogue;

import java.util.ArrayList;

public class YesNoDialogue extends AbstractDialogue{
	
	private Node currentNode;
	private int count;

	public YesNoDialogue(String url){
		count = 0;
		String[] lines = readFile(url);
		currentNode = generateTree(lines);
	}
	
	public void goToNextLine(DialogueObject choice) {
		YesNoDialogueObject thisChoice = (YesNoDialogueObject) choice;
		if (thisChoice.getChoice())
			currentNode = currentNode.children.get(0);
		else
			currentNode = currentNode.children.get(1);
	}

	public String getCurrentLine() {
		return currentNode.info;
	}

	public boolean isDone() {
		return currentNode.children.size() == 0;
	}
	
	private Node generateTree(String[] lines){
		String message = lines[count];
		count ++;
		Node thisNode = new Node();
		thisNode.children = new ArrayList<Node>();
		if (!(message.startsWith("##Q "))){
			thisNode.info = message;
		}
		else {
			thisNode.info = message.substring(4);
			thisNode.children.add(generateTree(lines));
			thisNode.children.add(generateTree(lines));
		}
		return thisNode;
	}
	
	public class YesNoDialogueObject extends DialogueObject{
		
		boolean choice;
		
		/**
		 * moves to one of two possible next lines in the dialogue based on the boolean choice
		 * @param choice
		 */
		public YesNoDialogueObject(boolean choice){
			this.choice = choice;
		}

		public boolean getChoice() {
			return choice;
		}
		
	}

}
