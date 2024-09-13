"""
Feature List for Image conversion arguments
"""

FEATURE_LIST = ["grayScale", "Purple", "Saffron", "Color", 
                "Side_Mirror", "Top_Mirror", "Contrast", "Indian_Flag", 
                "RandomPix", "RandomPix2", "SwitchPix", "SwitchRandPix"]

#TODO: add feature to execute java commands directly to the terminal
#TODO: add loading screen feature for image conversion

def command_choice(flist):
    print("Do you want conversion commands? (Y/N)")
    inp = str(input())
    if inp == "Y":
        counter = 1
        for filter in flist:
            print(str(counter)+") java ImageStyle "+filter)
            counter += 1
    elif inp == "N":
        print("END")

def main(flist=FEATURE_LIST):
    print("Filters for Image conversion:")
    for filter in flist:
        print(filter)
    command_choice(flist)


if __name__ == "__main__":
    main()