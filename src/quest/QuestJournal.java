package quest;

import gameCharacter.GameCharacter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import app.RPGame.Pausable;

import com.golden.gamedev.object.font.SystemFont;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class QuestJournal
{
	private ArrayList<Quest> myCurrentQuests;
	private ArrayList<Quest> myCompletedQuests;
	private boolean showJournal = false;
	public static final int INDENT = 10;
	public static final Color journalColor = Color.darkGray;
	private static final int journalSlots = 9;
	private GameCharacter gC;
	
	public QuestJournal(GameCharacter gC)
	{
		myCurrentQuests = new ArrayList<Quest>();
		myCompletedQuests = new ArrayList<Quest>();
		this.gC = gC;
		gC.setJournal(this);
	}
	
	public void addQuest(Quest qu, QuestObserver qg)
	{
		myCurrentQuests.add(qu);
		qu.addObserver(qg);
	}
	
	public void completeQuest(Quest qu)
	{
		qu.completeQuest(gC);
		myCurrentQuests.remove(qu);
		myCompletedQuests.add(qu);
	}
	
	public void removeQuest(Quest qu)
	{
		myCurrentQuests.remove(qu);
	}

	public void update(long elapsed) 
	{
		if (myCurrentQuests.size() > 0)
		{
			myCurrentQuests.get(0).update();
			if(myCurrentQuests.get(0).checkComplete())
				completeQuest(myCurrentQuests.get(0));
		}
	
		if (gC.getGame().keyPressed(java.awt.event.KeyEvent.VK_J))
		{
			if (gC.getGame().isPausedFor(Pausable.JOURNAL))
			{
				gC.getGame().unPauseGameFor(Pausable.JOURNAL);
				showJournal = false;
			}
			else
			{
				gC.getGame().pauseGameFor(Pausable.JOURNAL);
				showJournal = true;
			}
		}
	}
	
	public void setQuests(ArrayList<Quest> quests)
	{
		myCurrentQuests = quests;
	}
	
	public void render(Graphics2D g)
	{
		if (!showJournal)
			return;
		
		for (Quest qu: myCurrentQuests)
		{
			qu.update();
		}

		 SystemFont font = new SystemFont(new Font("Arial", Font.BOLD, 12), new Color(255, 255, 255));
		 
		 drawQuestSlots(g);
		 
		 font.drawString(g, "Quest Log", SystemFont.LEFT, INDENT, 40, 70);
		 
		 for (int i = 0; i < journalSlots; i++)
		 {
			 if (myCurrentQuests.size() > i)
			 font.drawString(g, myCurrentQuests.get(i).getDescription(), SystemFont.LEFT, INDENT, 65+25*i, 70);
		 }
	}
	
	public JsonObject toJson()
	{
		JsonObject json = new JsonObject();
		
		json.add("character", new JsonPrimitive(gC.getName()));
		
		JsonArray jArray = new JsonArray();
		
		for (Quest qu: myCurrentQuests)
		{
			jArray.add(qu.toJson());
		}
		
		json.add("quests", jArray);
		
		return json;
	}
	
	
    private void drawQuestSlots (Graphics2D g) 
    {	

        g.setColor(journalColor);
        
        for (int i = 0; i < 10; i++)
        {
            g.drawRect(5, 40+25*i, 70, 20); 
            g.fillRect(5, 40+25*i, 70, 20);
        }
        
    }
}
