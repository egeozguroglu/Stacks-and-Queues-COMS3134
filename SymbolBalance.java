/** SymbolBalance.java
 * Data Structures in Java (COMS3134) - Fall 2020 - HW2
 * @Author: Ege Ozguroglu (uni: eo2464)
 * @Date: 10/16/2020
 */
import java.io.File;
import java.io.LineNumberReader;
// https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
// https://docs.oracle.com/javase/7/docs/api/java/io/LineNumberReader.html
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SymbolBalance implements SymbolBalanceInterface {
    
    // instance variables: 
    private LineNumberReader lr;
    private FileReader fr;
    private MyStack<Character> theStack;

    public void setFile(String filename){
        
        try{
            // FileReader object around filename:
            fr = new FileReader(filename);
            // LineNumberReader object wrapped around the FileReader object.
            lr = new LineNumberReader(fr); 
        }
        catch(FileNotFoundException e){
            System.err.println(filename + " could not be opened.");
            System.exit(-1);
        }
    }
    
    @SuppressWarnings("unchecked")
	public BalanceError checkFile(){
        theStack = new MyStack<Character>();
        BalanceError error = null;
        // the BufferedReader read method returns an int in the range 0 to 65535 (0x00-0xffff), 
        // When end-of-stream, returns -1.
        int i;
        char currentSymbol;
        char symbolPopped;
        
        try{
            while((i = lr.read()) != -1){
                // casting from int to corresponding char
                currentSymbol = (char) i;

                // if the character is an openning {, ( or [ symbol:
                if (currentSymbol == '{' || currentSymbol == '(' || currentSymbol == '['){
                    theStack.push(currentSymbol);
                    // continue to the next iteration of the while loop, i.e. read off the next character. 
                    continue;
                }
                // if the character is a closing }, ) or ] symbol:
                else if(currentSymbol == '}' || currentSymbol == ')' || currentSymbol == ']'){
                    if(!theStack.isEmpty()){
                        symbolPopped = (char) theStack.pop();
                        if(currentSymbol == '}'){
                            if(symbolPopped == '{'){
                                continue;
                            }
                            else{
                                error = new MismatchError((lr.getLineNumber()+1), currentSymbol, symbolPopped);
                                return error;
                            }
                        }
                        else if(currentSymbol == ')'){
                            if(symbolPopped == '('){
                                continue;
                            }
                            else{
                                error = new MismatchError((lr.getLineNumber()+1), currentSymbol, symbolPopped);
                                return error;
                            }
                        }
                        else if(currentSymbol == ']'){
                            
                            if(symbolPopped == '['){
                                continue;
                            }
                            else{
                                error = new MismatchError((lr.getLineNumber()+1), currentSymbol, symbolPopped);
                                return error;
                            }   
                        }
                    }
                    else{
                        // There is a closing }, ) or ] symbol without an opening symbol.
                        error = new EmptyStackError((lr.getLineNumber() + 1));
                        return error;
                    }
                }
                // if the character is /, opening /* is possible.
                // Check if the next character is an asterisk symbol.
                else if(currentSymbol == '/'){
                    // read the next character:
                    currentSymbol = (char) lr.read();
                    // if the next character is an asterisk, a comment block is starting.
                    // push an asterisk into theStack, then ignore all character until the end of
                    // the commment block, i.e. until reaching "*/" 
                    if(currentSymbol == '*'){
                        theStack.push(currentSymbol);
                        // ignore all characters until the multiline comment is closed:
                        while( (i = lr.read()) != -1){
                            currentSymbol = (char) i;
                            // if the character is an asterisk, closing */ is possible.
                            if(currentSymbol == '*'){
                                // read the next character:
                                currentSymbol = (char) lr.read();
                                // check if the next character is a '/'.
                                if(currentSymbol == '/'){
                                    // remove the asterisk representing the comment opener from theStack.
                                    theStack.pop();
                                    break;
                                }
                            }
                        }
                    }
                }
                else if(currentSymbol == '*'){
                    // read the next character:
                    currentSymbol = (char) lr.read();
                    if(currentSymbol == '/'){
                        if(!theStack.isEmpty()){
                           symbolPopped = (char) theStack.pop();
                            if(symbolPopped != '*'){
                                error = new MismatchError((lr.getLineNumber()+1), currentSymbol, symbolPopped);
                            }
                        }
                        // there's a closing */ symbol without its opener. 
                        else{
                            error = new EmptyStackError((lr.getLineNumber() + 1));
                        } 
                    }
                }

                // if a " symbol is read, continue iterating until another " is read. 
                // That is, ignore the characters within literal strings. 
                else if(currentSymbol == '"'){
                    theStack.push(currentSymbol);
                    // ignore all characters until currentSymbol closes the "":
                    while( (i = lr.read()) != -1){
                        currentSymbol = (char) i;
                        // break if a " is reached: 
                        if(currentSymbol == '"'){
                            theStack.pop();
                            break;
                        }
                    }
                }
                // check if there's a closing " symbol without its opening.
                else if(currentSymbol == '"'){
                    // read the next character:
                    currentSymbol = (char) lr.read();
                    if(currentSymbol == '"'){
                        if(!theStack.isEmpty()){
                            symbolPopped = (char) theStack.pop();
                            if(symbolPopped != '*'){
                                error = new MismatchError((lr.getLineNumber()+1), currentSymbol, symbolPopped);
                            }
                        }
                        else{
                             error = new EmptyStackError((lr.getLineNumber() + 1));
                        }
                    }
                }
            }
        }
        catch(IOException e){
            System.out.println("IO exception!");
        }
        
        // after reaching the end of the file, check if the stack is empty. 
        if(!theStack.isEmpty()){
            error = new NonEmptyStackError((char)theStack.peek(),theStack.size());
        }
        return error;
    } 
    // returns either MismatchError(int lineNumber, char currentSymbol, char symbolPopped)
	//                EmptyStackError(int lineNumber), 
	//                NonEmptyStackError(char topElement, int sizeOfStack). 
	// All three classes implement BalanceError
}