/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:  
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 *       Handles responding to simple words and phrases 
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie
{
 /**
  * Get a default greeting  
  * @return a greeting
  */
 public String getGreeting()
 {
  return "Hello, let's talk.";
 }
 
 /**
  * Gives a response to a user statement
  * 
  * @param statement
  *            the user statement
  * @return a response based on the rules given
  */
 public String getResponse(String statement)
 {
  String response = "";
  if (response.trim() == "    ") 
  {
    response = "Are you still there?";
 }
  else if (findKeyword(statement, "no", 0) >= 0)
  {
   response = "Why so negative?";
  }
  else if ((findKeyword(statement, "mother", 0) >= 0)
    || findKeyword(statement, "father", 0) >= 0
    || findKeyword(statement, "sister", 0) >= 0
    || findKeyword(statement, "brother", 0) >= 0)
  {
   response = "Tell me more about your family.";
  }
  else if ((findKeyword(statement, "dog", 0) >= 0)
            || findKeyword(statement, "cat", 0) >= 0)
             {
    response = "Tell me more about your pets.";
  }
 
  //it responds according to "no" first, replying "Why so negative?" To make it switched, I think 
  //I have to code something so that it catches and react to the first order keyword, 
  //in this case, dog and reply using "Tell me about your pets."
  
  else if ((findKeyword(statement, "Mr. Kiang", 0) >= 0)
             || findKeyword(statement, "Mr. Landgraf", 0) >= 0)
  {
    response = "He sounds like a nice teacher.";
  }
  else if ((findKeyword(statement, "hungry", 0) >= 0)
             || findKeyword(statement, "food", 0) >= 0)
  {
    response = "What kind of food do you like to eat?";
  }
  else if ((findKeyword(statement, "computer", 0) >= 0
             || findKeyword(statement, "phone", 0) >= 0))
  {
    response = "What do you usually do on that? Do you play any games?";
  }
  else
  {
   response = getRandomResponse();
  }
  return response;
 }

 /**
  * Pick a default response to use if nothing else fits.
  * @return a non-committal string
  */
 private String getRandomResponse()
 {
  final int NUMBER_OF_RESPONSES = 6;
  double r = Math.random();
  int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
  String response = "";
  
  if (whichResponse == 0)
  {
   response = "Interesting, tell me more.";
  }
  else if (whichResponse == 1)
  {
   response = "Hmmm.";
  }
  else if (whichResponse == 2)
  {
   response = "Do you really think so?";
  }
  else if (whichResponse == 3)
  {
   response = "You don't say.";
  }
  else if (whichResponse == 4)
  {
    response = "I see.";
  }
  else if (whichResponse == 5)
  {
    response = "Keep going...";
  }

  return response;
 }
private int findKeyword(String statement, String goal,
   int startPos)
 {
  String phrase = statement.trim();
  // The only change to incorporate the startPos is in
  // the line below
  int psn = phrase.toLowerCase().indexOf(
    goal.toLowerCase(), startPos);

  // Refinement--make sure the goal isn't part of a
  // word
  while (psn >= 0)
  {
   // Find the string of length 1 before and after
   // the word
   String before = " ", after = " ";
   if (psn > 0)
   {
    before = phrase.substring(psn - 1, psn)
      .toLowerCase();
   }
   if (psn + goal.length() < phrase.length())
   {
    after = phrase.substring(
      psn + goal.length(),
      psn + goal.length() + 1)
      .toLowerCase();
   }

   // If before and after aren't letters, we've
   // found the word
   if (((before.compareTo("a") < 0) || (before
     .compareTo("z") > 0)) // before is not a
           // letter
     && ((after.compareTo("a") < 0) || (after
       .compareTo("z") > 0)))
   {
    return psn;
   }

   // The last position didn't work, so let's find
   // the next, if there is one.
   psn = phrase.indexOf(goal.toLowerCase(),
     psn + 1);

  }

  return -1;
 }
}