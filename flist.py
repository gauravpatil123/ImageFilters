"""
Feature List for Image conversion arguments
"""

FEATURE_LIST = ["grayScale", "Purple", "Saffron", "Color", 
                "Side_Mirror", "Top_Mirror", "Contrast", "Indian_Flag", 
                "RandomPix", "RandomPix2", "SwitchPix", "SwitchRandPix"]

def command_choice(flist):
    print("Do you want conversion commands? (Y/N)")
    ans = str(input())
    if ans == "Y":
        counter = 1
        for filter in flist:
            print(counter+") java ImageStyle "+filter)
    elif ans == "N":
        print("END")

def main(flist=FEATURE_LIST):
    print("Filters for Image conversion:")
    for filter in flist:
        print(filter)


if __name__ == "__main__":
    main()