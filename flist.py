"""
Feature List for Image conversion arguments
"""
import os

FEATURE_LIST = ["grayScale", "Purple", "Saffron", "Color", 
                "Side_Mirror", "Top_Mirror", "Contrast", "Indian_Flag", 
                "RandomPix", "RandomPix2", "SwitchPix", "SwitchRandPix"]

FEATURE_DICT = {}

#TODO: add feature to execute java commands directly to the terminal
#TODO: add loading screen feature for image conversion ===? in progress on ls

def create_fdict(flist=FEATURE_LIST):
    global FEATURE_DICT
    for idx in range(len(flist)):
        id = idx + 1
        FEATURE_DICT[id] = flist[idx]
        #print(str(id)+" "+flist[idx]) #DONOT UNCOMMENT THIS IS ONLY FOR TESTING

def command_choice(flist:list):
    global FEATURE_DICT
    print("Do you want conversion commands? (Y/N)")
    inp = str(input())
    if inp == "Y":
        counter = 1
        for filter in flist:
            print(str(counter)+") java ImageStyle "+filter)
            counter += 1
        choice = int(input("Input filter choice number for image conversion."))
        slected_filter = FEATURE_DICT[choice]
        conv_command = "java ImageStyle "+ slected_filter
        print("Chosen filter is "+choice+"/nDo you want to proceed? (Y/N)")
        proceed = str(input())
        if proceed == "Y":
            pass
        elif proceed == "N":
            print("END")
    elif inp == "N":
        print("END")

def main(flist=FEATURE_LIST):
    create_fdict()
    print("Filters for Image conversion:")
    for filter in flist:
        print(filter)
    command_choice(flist)


if __name__ == "__main__":
    main()