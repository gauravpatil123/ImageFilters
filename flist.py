"""
Feature List for Image conversion arguments
"""

FEATURE_LIST = ["grayScale", "Purple", "Saffron", "Color", 
                "Side_Mirror", "Top_Mirror", "Contrast", "Indian_Flag", 
                "RandomPix", "RandomPix2", "SwitchPix", "SwitchRandPix"]


def main(flist=FEATURE_LIST):
    print("Filters for Image conversion:")
    for filter in flist:
        print(filter)


if __name__ == "__main__":
    main()