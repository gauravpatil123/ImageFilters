"""
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
#TODO: document the fuctions for future reference

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
    selected_filter = fdict[int(choice)]
    conv_command = "java ImageStyle "+ selected_filter
    return [selected_filter, conv_command]

def initiate_proceed(choice:list) -> str:
    selected_filter = choice[0]
    print("Chosen filter is "+selected_filter+"\nDo you want to proceed? (Y/N)")
    proceed = str(input())
    return proceed

def execute_choice(choice:list) -> None:
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