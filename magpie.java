import java.util.Random;
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
  else if (findKeyword(statement, "I want to", 0) >= 0)
  {
   response = transformIWantToStatement(statement);
  }
  //  Part of student solution
  else if (findKeyword(statement, "I want", 0) >= 0)
  {
   response = transformIWantStatement(statement);
  }
 else
  {

   // Look for a two word (you <something> me)
   // pattern
   int psn = findKeyword(statement, "you", 0);

   if (psn >= 0
     && findKeyword(statement, "me", psn) >= 0)
   {
    response = transformYouMeStatement(statement);
   }
   else
   {
    //  Part of student solution
    // Look for a two word (I <something> you)
    // pattern
    psn = findKeyword(statement, "i", 0);

    if (psn >= 0
      && findKeyword(statement, "you", psn) >= 0)
    {
     response = transformIYouStatement(statement);
    }
    else if (findKeyword(statement, "is", psn) >= 0)
    {
      response = transformIsWhyStatement(statement);
    }
    else if (findKeyword(statement, "are", psn) >= 0)
    {
      response = transformAreWhyStatement(statement);
    }
   else
   {
    response = getRandomResponse();
   }
  }
 }
  return response;
 }
 
 private String transformIWantToStatement(String statement)
 {
  //  Remove the final period, if there is one
  statement = statement.trim();
  String lastChar = statement.substring(statement
    .length() - 1);
  if (lastChar.equals("."))
  {
   statement = statement.substring(0, statement
     .length() - 1);
  }
  int psn = findKeyword (statement, "I want to", 0);
  String restOfStatement = statement.substring(psn + 9).trim();
  return "What would it mean to " + restOfStatement + "?";
 }

 private String transformIWantStatement(String statement)
 {
  //  Remove the final period, if there is one
  statement = statement.trim();
  String lastChar = statement.substring(statement
    .length() - 1);
  if (lastChar.equals("."))
  {
   statement = statement.substring(0, statement
     .length() - 1);
  }
  int psn = findKeyword (statement, "I want", 0);
  String restOfStatement = statement.substring(psn + 6).trim();
  return "Would you really be happy if you had " + restOfStatement + "?";
 }
  
 /**
  * Take a statement with "you <something> me" and transform it into 
  * "What makes you think that I <something> you?"
  * @param statement the user statement, assumed to contain "you" followed by "me"
  * @return the transformed statement
  */
 private String transformYouMeStatement(String statement)
 {
  //  Remove the final period, if there is one
  statement = statement.trim();
  String lastChar = statement.substring(statement
    .length() - 1);
  if (lastChar.equals("."))
  {
   statement = statement.substring(0, statement
     .length() - 1);
  }
  
  int psnOfYou = findKeyword (statement, "you", 0);
  int psnOfMe = findKeyword (statement, "me", psnOfYou + 3);
  
  String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
  return "What makes you think that I " + restOfStatement + " you?";
 }
 
 private String transformIYouStatement(String statement)
 {
  //  Remove the final period, if there is one
  statement = statement.trim();
  String lastChar = statement.substring(statement
    .length() - 1);
  if (lastChar.equals("."))
  {
   statement = statement.substring(0, statement
     .length() - 1);
  }
  
  int psnOfI = findKeyword (statement, "I", 0);
  int psnOfYou = findKeyword (statement, "you", psnOfI);
  
  String restOfStatement = statement.substring(psnOfI + 1, psnOfYou).trim();
  return "Why do you " + restOfStatement + " me?";
 }
 
//move "is" to the front, and "why" in front of "is"
 private String transformIsWhyStatement(String statement)
 {
   statement = statement.trim();
   String lastChar = statement.substring(statement.length() -1);
   if (lastChar.equals("."))
   {
     statement = statement.substring(0, statement.length() -1);
   }
   int psnOfIs = findKeyword (statement, "is", 0);
  //create a new string called begin that starts at the beginning and goes to the index of is
String beginSentence = statement.substring(0, psnOfIs);
//create a string called restofstatement that starts after the is and goes to the end.
   String restOfStatement = statement.substring(psnOfIs + 2);
      //set response = why is begin + end
   return "Why is " + beginSentence + (restOfStatement.trim()) + "?";
 }
 
 private String transformAreWhyStatement(String statement)
 {
    statement = statement.trim();
   String lastChar = statement.substring(statement.length() -1);
   if (lastChar.equals("."))
   {
     statement = statement.substring(0, statement.length() -1);
   }
   int psnOfAre = findKeyword (statement, "are", 0);
String beginSentence = statement.substring(0, psnOfAre);
   String restOfStatement = statement.substring(psnOfAre + 3);
      //set response = why is begin + end
   return "Why are " + beginSentence + (restOfStatement.trim()) + "?";
 }

 /**
  * Pick a default response to use if nothing else fits.
  * @return a non-committal string
  */
private String getRandomResponse ()
 {
  Random r = new Random ();
  return randomResponses [r.nextInt(randomResponses.length)];
 }
 
 private String [] randomResponses = {"Interesting, tell me more",
   "Hmmm.",
   "Do you really think so?",
   "You don't say.",
   "I see.",
   "Keep going...",
   "Well then.", 
   "Uh-huh...",
   "Ok then.",
   "Yes?",
 };
 
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
