//
//  ReviewCommentsViewController.m
//  Phresco
//
//  Created by Rojaramani on 11/10/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import "ReviewCommentsViewController.h"
#import "ReviewCommentsViewController.h"
#import "SharedObjects.h"
#import "DataModelEntities.h"
#import "ServiceHandler.h"
#import "AddToBagViewController.h"
//#import "SpecialOffersViewController.h"
#import "Tabbar.h"
#import "NavigationView.h"
#import "Constants.h"

@interface ReviewCommentsViewController ()

@end

@implementation ReviewCommentsViewController
@synthesize ratingImage;

@synthesize commentsLabel;
@synthesize dateLabel;
@synthesize disImage;
@synthesize userNameLabel;
@synthesize commentArray;
@synthesize specialOffersViewController;
@synthesize addToBagViewController;
@synthesize ratingCount;



- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    if(UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad)
	{
		self = [super initWithNibName:@"ReviewCommentsViewController-iPad" bundle:nil];
	}
	else 
    {
        self = [super initWithNibName:@"ReviewCommentsViewController" bundle:nil];
        
    }
    return self;
}


#pragma mark - View lifecycle

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    AssetsDataEntity *assetsData = [SharedObjects sharedInstance].assetsDataEntity;
    
    if(UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad) {
        
        tabbar = [[Tabbar alloc] initWithFrame:CGRectMake(0, 935, 768, 99)];
    }
    else {
        tabbar = [[Tabbar alloc] initWithFrame:kTabbarRect];
    }
    
    NSMutableArray *names = [NSMutableArray array];
    
    int startIndex = 0;
    
    int lastIndex = 4;
    
    for(int i = startIndex; i <= lastIndex; i++) {
        [names addObject:[assetsData.featureLayout objectAtIndex:i]];
    }
    
    //create tabbar with given features
    [tabbar initWithInfo:names];
    
    [self.view addSubview:(UIView*)tabbar];
    
    [tabbar setSelectedIndex:1 fromSender:nil];
    NavigationView *navBar=nil;
    if(UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPhone) {
        navBar = [[NavigationView alloc] initWithFrame:CGRectMake(0, 0, SCREENWIDTH, 40)];
    }
    else{
        navBar = [[NavigationView alloc] initWithFrame:CGRectMake(0, 0, SCREENWIDTH, 80)];
    }
    navBar.navigationDelegate = self;
    [navBar loadNavbar:YES:NO];
    [self.view addSubview:navBar];

    [self loadOtherViews];
    
    [self initializeTableView];
}

- (void)viewDidUnload
{
    [super viewDidUnload];
    
}
#pragma mark 
#pragma mark Navigation methods

-(void) loadOtherViews
{
    if(UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad)
    {
        UIImageView    *bgView = [[UIImageView alloc] initWithFrame:CGRectMake(0, 80, SCREENWIDTH, 860)];
        
        [bgView setImage:[UIImage imageNamed:@"home_screen_bg-72.png"]];
        
        [self.view addSubview:bgView];
        
        bgView = nil;
        
        UIImageView *searchBarView = [[UIImageView alloc] initWithFrame:CGRectMake(0,80 , SCREENWIDTH, 60)];
        
        [searchBarView setImage:[UIImage imageNamed:@"searchblock_bg-72.png"]];
        
        [self.view addSubview:searchBarView];
        
        searchBarView = nil;
        
        NSMutableArray *buttonArray = [[NSMutableArray alloc] initWithObjects:[UIImage imageNamed:@"browse_btn_highlighted.png"], [UIImage imageNamed:@"offers_btn_normal.png"],
                                       [UIImage imageNamed:@"mycart_btn_normal.png"], 
                                       nil];
        
        int x  = 8;
        
        int y = 83;
        
        int width = 250;
        
        int height = 55;
        
        for(int i = 0; i<[buttonArray count]; i++)
        {
            UIButton *button = [[UIButton alloc] init];
            
            [button setFrame:CGRectMake(x, y, width, height)];
            
            [button setBackgroundImage:[buttonArray objectAtIndex:i] forState:UIControlStateNormal];
            
            [self.view addSubview:button];
            
            x = x + 252;
            
            if(i==1)
            {
                [button addTarget:self action:@selector(specialOfferButtonPressed:) forControlEvents:UIControlEventTouchUpInside];
            }
            if(i==2)
            {
                [button addTarget:self action:@selector(myCartButtonPressed:) forControlEvents:UIControlEventTouchUpInside];
            }            
            button = nil;
            
        }
        
        
    }
    else {
        UIImageView    *bgView = [[UIImageView alloc] initWithFrame:CGRectMake(0, 40, SCREENWIDTH, 375)];
        
        [bgView setImage:[UIImage imageNamed:@"home_screen_bg.png"]];
        
        [self.view addSubview:bgView];
        
        bgView = nil;
        
        UIImageView *searchBarView = [[UIImageView alloc] initWithFrame:CGRectMake(0, 40 ,SCREENWIDTH, 40)];
        
        [searchBarView setImage:[UIImage imageNamed:@"searchblock_bg.png"]];
        
        [self.view addSubview:searchBarView];
        
        searchBarView = nil;
        
        NSMutableArray *buttonArray = [[NSMutableArray alloc] initWithObjects:[UIImage imageNamed:@"browse_btn_highlighted.png"], [UIImage imageNamed:@"offers_btn_normal.png"],
                                       [UIImage imageNamed:@"mycart_btn_normal.png"], 
                                       nil];
        
        int x  = 5;
        
        int y = 42;
        
        int width = 100;
        
        int height = 35;
        
        for(int i = 0; i<[buttonArray count]; i++)
        {
            UIButton *button = [[UIButton alloc] init];
            
            [button setFrame:CGRectMake(x, y, width, height)];
            
            [button setBackgroundImage:[buttonArray objectAtIndex:i] forState:UIControlStateNormal];
            
            [self.view addSubview:button];
            
            x = x + 102;
            
            if(i==1)
            {
                [button addTarget:self action:@selector(specialOfferButtonPressed:) forControlEvents:UIControlEventTouchUpInside];
            }
            if(i==2)
            {
                [button addTarget:self action:@selector(myCartButtonPressed:) forControlEvents:UIControlEventTouchUpInside];
            }            
            button = nil;
            
        }
        
    }
}
-(void) initializeTableView
{
    
    if(UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad)
	{
        
        userNameLabel = [[UILabel alloc] initWithFrame:CGRectMake(500, 200, 300, 100)];
        [userNameLabel setFont:[UIFont fontWithName:@"Helvetica" size:30]];
        userNameLabel.backgroundColor = [UIColor clearColor];
        userNameLabel.textColor = [UIColor yellowColor];
        [self.view addSubview:userNameLabel];
        
        commentsLabel = [[UILabel alloc] initWithFrame:CGRectMake(20, 320, 560, 120)];
        [commentsLabel setFont:[UIFont fontWithName:@"Helvetica" size:30]];
        commentsLabel.backgroundColor = [UIColor clearColor];
        commentsLabel.numberOfLines = 3;
        commentsLabel.textColor = [UIColor whiteColor];
        [self.view addSubview:commentsLabel];
        
        dateLabel = [[UILabel alloc] initWithFrame:CGRectMake(commentsLabel.frame.origin.x,commentsLabel.frame.origin.y+ commentsLabel.frame.size.height + 20, 400, 60)];
        [dateLabel setFont:[UIFont fontWithName:@"Helvetica" size:30]];
        dateLabel.backgroundColor = [UIColor clearColor];
        dateLabel.textColor = [UIColor whiteColor];
        [self.view addSubview:dateLabel];  
        
        
        float x = 20;
        
        float  y =  200;
        
        float  width = 40;
        
        float height = 40;
        
        NSMutableArray *imageFramesWhiteArray = [[NSMutableArray alloc]init];
        for(int i = 0; i<5;i++)
        {
            UIImageView *ratingsView = [[UIImageView alloc]init];
            ratingsView.frame = CGRectMake(x,y,width,height);
            [ratingsView setImage:[UIImage imageNamed:@"white_star.png"]];
            x = x + 40;
            [ratingsView setTag:i];
            [self.view  addSubview:ratingsView];
            [imageFramesWhiteArray addObject:ratingsView];
        }
        
    }
    else{
        
        
        userNameLabel = [[UILabel alloc] initWithFrame:CGRectMake(180, 100, 100, 25)];
        [userNameLabel setFont:[UIFont fontWithName:@"Helvetica" size:12]];
        userNameLabel.backgroundColor = [UIColor clearColor];
        userNameLabel.textColor = [UIColor yellowColor];
        [self.view addSubview:userNameLabel];
        
        commentsLabel = [[UILabel alloc] initWithFrame:CGRectMake(10, 127, 280, 65)];
        [commentsLabel setFont:[UIFont fontWithName:@"Helvetica" size:12]];
        commentsLabel.backgroundColor = [UIColor clearColor];
        commentsLabel.numberOfLines = 3;
        commentsLabel.textColor = [UIColor whiteColor];
        [self.view addSubview:commentsLabel];
        
        dateLabel = [[UILabel alloc] initWithFrame:CGRectMake(commentsLabel.frame.origin.x,commentsLabel.frame.origin.y+ commentsLabel.frame.size.height + 10, 200, 30)];
        [dateLabel setFont:[UIFont fontWithName:@"Helvetica" size:12]];
        dateLabel.backgroundColor = [UIColor clearColor];
        dateLabel.textColor = [UIColor whiteColor];
        [self.view addSubview:dateLabel];  
        
        
        float x = 10;
        
        float  y =  100;
        
        float  width = 15;
        
        float height = 15;
        NSMutableArray *imageFramesWhiteArray = [[NSMutableArray alloc]init];
        for(int i = 0; i<5;i++)
        {
            UIImageView *ratingsView = [[UIImageView alloc]init];
            ratingsView.frame = CGRectMake(x,y,width,height);
            [ratingsView setImage:[UIImage imageNamed:@"white_star.png"]];
            x = x + 15;
            [ratingsView setTag:i];
            [self.view  addSubview:ratingsView];
            [imageFramesWhiteArray addObject:ratingsView];
        }
        
    }
    
}

-(void)specialOfferButtonPressed:(id)sender
{
    NSMutableArray *buttonArray = [[NSMutableArray alloc] initWithObjects:[UIImage imageNamed:@"browse_btn_normal.png"], [UIImage imageNamed:@"offers_btn_highlighted.png"], [UIImage imageNamed:@"mycart_btn_normal.png"], 
                                   nil];
    
    
    int x  = 5;
    
    int y = 42;
    
    int width = 100;
    
    int height = 35;
    
    for(int i = 0; i<[buttonArray count]; i++)
    {
        UIButton *button = [[UIButton alloc] init];
        
        [button setFrame:CGRectMake(x, y, width, height)];
        
        [button setBackgroundImage:[buttonArray objectAtIndex:i] forState:UIControlStateNormal];
        
        [self.view addSubview:button];
        
        x = x + 102;
        
        
        button = nil;
        
    }
    
    ServiceHandler *serviceHandler = [[ServiceHandler alloc] init];
    
    [serviceHandler specialProductsService:self :@selector(finishedSpecialProductsService:)];
    
    serviceHandler = nil;
    
}


-(void) finishedSpecialProductsService:(id) data
{
    AssetsDataEntity *assetsData = [SharedObjects sharedInstance].assetsDataEntity;
    
    [assetsData updateSpecialproductsModel:data];
    
    
//    SpecialOffersViewController *tempSpecialOffersViewController = [[SpecialOffersViewController alloc] initWithNibName:@"SpecialOffersViewController" bundle:nil];
//    
//    self.specialOffersViewController = tempSpecialOffersViewController;
//    
//    [self.view addSubview:specialOffersViewController.view];
//    
//    tempSpecialOffersViewController = nil;
}

- (void) myCartButtonPressed:(id)sender
{
    NSMutableArray *buttonArrayForMycart = [[NSMutableArray alloc] initWithObjects:
                                            [UIImage imageNamed:@"browse_btn_normal.png"],                                        [UIImage imageNamed:@"offers_btn_normal.png"],
                                            [UIImage imageNamed:@"mycart_btn_highlighted.png"],nil];
    int x  = 5;
	
	int y = 42;
	
	int width = 100;
	
	int height = 35;
	
	for(int i = 0; i<[buttonArrayForMycart count]; i++)
	{
		UIButton *button = [[UIButton alloc] init];
		
		[button setFrame:CGRectMake(x, y, width, height)];
		
		[button setBackgroundImage:[buttonArrayForMycart objectAtIndex:i] forState:UIControlStateNormal];
		
		[self.view addSubview:button];
		
		x = x + 102;
        
       	button = nil;
		
	}
    
    AddToBagViewController *tempResultViewController = [[AddToBagViewController alloc] initWithNibName:@"AddToBagViewController" bundle:nil];
	
	self.addToBagViewController = tempResultViewController;
    
	[self.view addSubview:addToBagViewController.view];
    
	tempResultViewController = nil;
    
}


-(void)goBack:(id)sender
{
    [self.view removeFromSuperview];
}

- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation
{
    
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
- (void)didReceiveMemoryWarning
{
    
    [super didReceiveMemoryWarning];
}
-(void)backButtonAction{
    [self.view removeFromSuperview];
    
}

@end

