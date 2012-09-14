package dialogue;

import java.util.ArrayList;

public class MultipleChoiceDialogue extends AbstractDialogue{
	
	private Node currentNode;
	private int count;

	public MultipleChoiceDialogue(String url){
		String[] lines = readFile(url);
		currentNode = generateTree(lines);
	}
	
	@Override
	public void goToNextLine(DialogueObject choice) {
		MultipleChoiceDialogueObject thisChoice = (MultipleChoiceDialogueObject) choice;
		currentNode.children.get(thisChoice.getChoice());
	}

	@Override
	public String getCurrentLine() {
		return currentNode.info;
	}

	@Override
	public boolean isDone() {
		return currentNode.children.size() == 0;
	}
	
	private Node generateTree(String[] lines){
		String message = lines[count];
		count ++;
		Node thisNode = new Node();
		thisNode.children = new ArrayList<Node>();
		if (!(message.startsWith("##Q"))){
			thisNode.info = message;
		}
		else {
			thisNode.info = message.substring(4);
			thisNode.children.add(generateTree(lines));
			thisNode.children.add(generateTree(lines));
		}
		return thisNode;
	}
	
	public class MultipleChoiceDialogueObject extends DialogueObject{
		
		int choice;
		
		public MultipleChoiceDialogueObject(int choice){
			this.choice = choice;
		}

		public int getChoice() {
			return choice;
		}
		
	}

}
