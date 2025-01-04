"""
Script: flist.py
Author: GP

Notes: //

Feature List for Image conversion arguments
"""
import os

#TODO: make FEATURE_LIST variable generator fxn by importing external datafile(csv mostly)

FEATURE_LIST = ["grayScale", "Purple", "Saffron", "Color", 
                "Side_Mirror", "Top_Mirror", "Contrast", "Indian_Flag", 
                "RandomPix", "RandomPix2", "SwitchPix", 
                "SwitchRandPix", "4Styles", "Rd4Styles"]

FEATURE_DICT = {}

#TODO: add image hide feature and improve terminal interface

def create_fdict(flist:list=FEATURE_LIST) -> None:
    """
    Input:
        flist: list of features
    Action:
        creates a dictionary of ids and features
    """
    global FEATURE_DICT
    for idx in range(len(flist)):
        id = idx + 1
        FEATURE_DICT[id] = flist[idx]

def display_filters(counter:int, flist:list) -> None:
    """
    Input:
        counter: int representing the counter number
        flist: feature list 
    Action:
        prints a list of counter number and the corresponding features
    """
    for filter in flist:
        print(str(counter)+") "+filter)
        counter += 1

def authenticate_choice(fdict:dict) -> list:
    """
    Input:
        fdict: dictionary with index as keys and features as value
    Output:
        Alist of slected_filter and conv_command
        selected_filter: the string value of feature from fdict
        conv_command: string command to execute the selected filter 
                        from the java class in cammand line terminal
    Action:
        takes the input geature number and outputs the list of 
        selected filter and corresponding command to execute java class
    """
    choice = input("Input filter choice number for image conversion.\n")
    selected_filter = fdict[int(choice)]
    conv_command = "java ImageStyle "+ selected_filter
    return [selected_filter, conv_command]

def initiate_proceed(choice:list) -> str:
    """
    Input:
        choice: a lisy of string containing a selected choice filter & 
                corresponding java class command
    Output:
        proceed: string input from command line inorder 
                to feed in the execution loop
    """
    selected_filter = choice[0]
    print("Chosen filter is "+selected_filter+"\nDo you want to proceed? (Y/N)")
    proceed = str(input())
    return proceed

def execute_choice(choice:list) -> None:
    """
    Input:
        choice: List of use input choice arguments containing choice filter &
                corresponding java exec class command
    Action:
        initialtes and executes initiate_procedd fxn
        and using user choice to determine whether or not to proceed 
        with choice execution using the java class command
    """
    proceed = initiate_proceed(choice)
    conv_command = choice[1]
    if proceed == "Y" or proceed == "y":
        os.system(conv_command)
    elif proceed == "N" or proceed == "n":
        print("END")
    else:
        print("Invalid Choice: Try again")
        execute_choice(choice)

def command_choice(flist:list) -> None:
    """
    Input:
        flist: feature list
    Action:
        displays list of executable features
        authenticates user's choice
        executes the users choice
    """
    global FEATURE_DICT
    counter = 1
    display_filters(counter, flist)
    choice = authenticate_choice(FEATURE_DICT)
    execute_choice(choice)

def main(flist:list=FEATURE_LIST) -> None:
    """
    Input:
        flist: feature list
    Action:
        main fxn
        creates feature dictionary
        executes command_choice fxn
    """
    create_fdict()
    print("Filters for Image conversion:")
    command_choice(flist)

if __name__ == "__main__":
    main()