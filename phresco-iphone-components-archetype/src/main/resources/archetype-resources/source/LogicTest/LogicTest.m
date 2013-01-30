//
//  LogicTest.m
//  LogicTest
//
//  Created by Rojaramani on 30/10/12.
//
//

#import "LogicTest.h"

@implementation LogicTest

- (void)setUp
{
    [super setUp];
    
}

- (void)tearDown
{
    [super tearDown];
}

- (void)testExample
{
    [rootController tabBarButtonAction:@"0"];
}
-(void) testAction
{
    [homeController buttonAction:@"0"];
    
}
-(void) testBrowse
{
    NSIndexPath* index =[NSIndexPath indexPathWithIndex:-1];
    STAssertThrows([browseController tableView:tblView didSelectRowAtIndexPath:index] , @"Invalid index");
}
-(void) testProductResult
{
    [resultController  tableView:tblView didSelectRowAtIndexPath:0];
    [resultController reviewButtonSelected:@"0"];
    
}
-(void) testProductDetail
{
    [pdtDetailController addToCart:@"0"];
    
}

-(void) testAddToCart
{
    [addCartController removeIndex:@"0"];
    
}
-(void) testViewCart
{
    [viewCartController browseButtonSelected:@"0"];
    
}
-(void) testCheckCart
{
    [checkViewController cancelAction:@"0"];
    
}
-(void) testCheckOverall
{
    [checkViewController reviewAction:@"0"];
    
}
-(void) testReview
{
    [reviewController tableView:tblView didSelectRowAtIndexPath:0];
    
}
-(void) testComments
{
    [reviewCommentsController goBack:0];
    
}

-(void) testLogin
{
    [loginController registerButtonSelected:0];
    
}

-(void) testRegister
{
    [registerController registerButtonSelected:0];
    
}

-(void) testSplOffers
{
    [splOfferController tableView:tblView didSelectRowAtIndexPath:0];
    
}

@end
