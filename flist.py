"""
Feature List for Image conversion arguments
"""
import os

FEATURE_LIST = ["grayScale", "Purple", "Saffron", "Color", 
                "Side_Mirror", "Top_Mirror", "Contrast", "Indian_Flag", 
                "RandomPix", "RandomPix2", "SwitchPix", 
                "SwitchRandPix", "4Styles", "Rd4Styles"]

FEATURE_DICT = {}

#TODO: add image hide feature and improve terminal interface

def create_fdict(flist:list=FEATURE_LIST) -> None:
    global FEATURE_DICT
    for idx in range(len(flist)):
        id = idx + 1
        FEATURE_DICT[id] = flist[idx]

def display_filters(counter:int, flist:list) -> None:
    for filter in flist:
        print(str(counter)+") "+filter)
        counter += 1

def authenticate_choice(fdict:dict) -> list:
    choice = input("Input filter choice number for image conversion.\n")
    slected_filter = fdict[int(choice)]
    conv_command = "java ImageStyle "+ slected_filter
    print("Chosen filter is "+slected_filter+"\nDo you want to proceed? (Y/N)")
    proceed = str(input())
    return [proceed, conv_command]

def execute_choice(choice:list) -> None:
    proceed = choice[0]
    if proceed == "Y" or proceed == "y":
        conv_command = choice[1]
        os.system(conv_command)
    elif proceed == "N" or proceed == "n":
        print("END")
    else:
        print("Invalid Choice: Try again")
    #TODO: add one more else condition to handle other inputs and use reccursion

def command_choice(flist:list) -> None:
    global FEATURE_DICT
    counter = 1
    display_filters(counter, flist)
    choice = authenticate_choice(FEATURE_DICT)
    execute_choice(choice)

def main(flist:list=FEATURE_LIST) -> None:
    create_fdict()
    print("Filters for Image conversion:")
    command_choice(flist)

if __name__ == "__main__":
    main()