//
//  DataModelEntities.h
//  Phresco
//
//  Created by Rojaramani on 03/10/12.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>
//////////Configuration file constants

#define kappConfig							@"appConfig"
#define kfeatureLayout						@"featureLayout"
#define kfeatureConfig						@"featureConfig"

#define kfeatureName						@"featureName"
#define kfeatureIcon						@"featureIcon"
#define kdefaultIcon						@"defaultIcon"
#define kdefaultTab							@"defaultTab"
#define khighlightedTab						@"highlightedTab"

#define kiPhoneFeatureImagePaths            @"iPhoneFeatureImagePaths"
#define k3G                                 @"3G"

#define kImagesDict							@"ImagesDict"
#define kfeatureUrl                         @"featureUrl"
#define kdeepLinkPatternMatch               @"deepLinkPatternMatch"
#define kconfigVersion						@"configVersion"
#define kappVersionInfoDict                 @"appVersionInfo"
#define ksupportedVersions					@"supportedVersions"
#define kpoorNetwork						@"poorNetwork"
#define kpoorWifiMaxSecsLatency				@"poorWifiMaxSecsLatency"
#define kpoorNonWifiMaxSecsLatency			@"poorNonWifiMaxSecsLatency"

#define kCatalogId                          @"id"
#define kCatalogName                        @"name"
#define kCatalogImage                       @"image"
#define kCatalogCount                       @"productCount"


#define kProductId                          @"id"
#define kProductName                        @"name"
#define kproductImage                       @"image"
#define kproductDetailImage                 @"detailImage"
#define kproductDesc                        @"description"

#define kproductPrice                       @"listPrice"
#define kproductRating                      @"rating"
#define kName                               @"name"

#define kproductTVType                      @"TV Type"
#define kproductScreen                      @"Screen Size"
#define kProductRatio                       @"Screen Ratio"
#define kProductDefinition                  @"TV Definition"
#define kreview                             @"reviews"
#define kuserID                             @"userId"
#define kuserComments                       @"comment"
#define kratings                            @"ratings"
#define kaverage                            @"average"
#define kkey                                @"key"
#define kvalue                              @"value"
#define kuserName                           @"user"
#define kCommentedDate                      @"commentDate"
#define kuserId                             @"userid"
#define kloginUserName                      @"userName"

#define klogin                              @"login"
#define kloginEmail                         @"loginEmail"
#define kpassword                           @"password"
#define kfirstName                          @"firstName"
#define klastName                           @"lastName"
#define kemailAddress                       @"email"
#define kconfirmPassword                    @"password"
#define kphoneNumber                        @"phoneNumber"

#define korderproduct                       @"product"
#define korderdetail                        @"orderdetail"

#define kspecialProductId                   @"id"
#define kspecialProductName                 @"name"
#define kspecialProductImage                @"image"
#define kspecialProductRating               @"rating"
#define kspecialProductDesc                 @"description"
#define kspecialProductPrice                @"listPrice"

#define kReview                             @"review"
#define kpostReviewProductId                @"productId"
#define kpostReviewUserId                   @"userId"
#define kpostReviewComment                  @"comment"
#define kpostReviewCommentDate              @"commentDate"
#define kpostReviewRating                   @"rating"
#define kpostReviewUserName                 @""
#define ksearch                             @"search"
#define ksearchContext                      @"user"

@interface FeaturedAsset :NSObject<NSCoding> {
    
    
    
    NSString	*featureName;
	NSString	*defaultIconUrl;
	NSString	*defaultTabUrl;
	NSString	*defaultHighlightedTabUrl;
    NSString    *featureUrl;
    NSString    *deepLinkPatternMatch;
	NSMutableDictionary *imagesDict;
	BOOL		isDownloaded;
	BOOL        isInTabbar;
	NSInteger	sortWeight;	
    id          viewControler;

}
@property(nonatomic, copy) NSString	*featureName;
@property (nonatomic, copy) NSString	*defaultIconUrl;
@property (nonatomic, copy) NSString	*defaultTabUrl;
@property (nonatomic, copy) NSString	*defaultHighlightedTabUrl;
@property (nonatomic, copy) NSString    *featureUrl;
@property (nonatomic, copy) NSString    *deepLinkPatternMatch;
@property (nonatomic, strong) NSMutableDictionary *imagesDict;
@property  (nonatomic, assign) BOOL       isInTabbar;
@property (nonatomic, strong) id         viewControler;

- (void)setProperties:(NSDictionary*) dict;
@end

// contains assets other than Featured Assets(Header, More page layout etc)
@interface ExtraAssets : NSObject
{
	NSString *imageName;
	NSString *imageUrl;
}
@property (nonatomic, strong) NSString *imageName;
@property (nonatomic, strong) NSString *imageUrl;

- (void)setProperties:(NSDictionary*) dict;

@end


//contains Assets for TabBar and MorePage
@interface AssetsDataEntity : NSObject {
	
	NSMutableArray		*assets;
	
	NSMutableArray *extraAssets;
	
	NSMutableDictionary *headerImagesDict;
	
	NSMutableDictionary *appInfo;
	
	NSArray *featureLayout;
    
    NSMutableDictionary *catalogDictionary;
    
    NSMutableArray *catalogArray;
    
    NSMutableArray *productArray;
    
    NSMutableArray *productDetailArray;
    
    NSMutableDictionary *dictCart;
    
    NSMutableArray *arrayAddtoCart;
    
    NSMutableArray *productReviewArray;
    
    NSMutableArray *specialProductsArray;
    
    NSMutableArray *searchProductsArray;
    
    NSMutableArray* keyValueArray;
    
    NSMutableArray *reviewCommentsArray;
    
    NSMutableArray* reviewDictionary;
}

@property(nonatomic, strong) NSMutableArray		*assets;
@property(nonatomic, strong) NSMutableDictionary *appInfo;
@property(nonatomic, strong) NSArray			*featureLayout;
@property(nonatomic, strong) NSMutableArray     *extraAssets;
@property(nonatomic, strong) NSMutableDictionary *headerImagesDict;
@property(nonatomic, strong) NSMutableDictionary *catalogDictionary;
@property(nonatomic, strong) NSMutableArray *catalogArray;
@property(nonatomic, strong) NSMutableArray  *productArray;
@property(nonatomic, strong) NSMutableArray  *productDetailArray;
@property(nonatomic, strong) NSMutableDictionary *dictCart;
@property (nonatomic, strong)  NSMutableArray *arrayAddtoCart;
@property (nonatomic, strong) NSMutableArray *productReviewArray;
@property (nonatomic, strong) NSMutableArray *specialProductsArray;
@property (nonatomic, strong) NSMutableArray *searchProductsArray;
@property (nonatomic, strong) NSMutableArray* keyValueArray;
@property (nonatomic, strong) NSMutableArray* reviewCommentsArray;
@property (nonatomic, strong) NSMutableArray* reviewDictionary;
-(NSString*)featureNameWithIndex:(NSInteger)index;

///////// Extra Assets //////////
-(void) updateExtraAssetsModel:(NSDictionary*)data;

-(void) startLoadingExtraAssets:(id)object;
/////////////////////////////////

-(void) updateCatalogModel:(NSMutableArray*) data;

-(int)indexOfAssetWithName:(NSString*)name;

-(BOOL)isFeatureNative:(NSString*)name;

-(FeaturedAsset*)objectForAssetName:(NSString*)name;

-(void)updateFeaturedAssetModel:(id)data;

-(void)updatedAppFeatureLayout:(NSDictionary*)dict;

-(void)startLoadingFeaturedAssets:(id)object;

-(UIButton*)createButton:(NSString*)name willAddWiggle:(BOOL)addWiggle;


-(void) updateProductModel:(NSMutableArray*) data;

-(void) updateProductDetailsModel:(NSMutableArray*) data;

-(void) updateProductReviewModel:(NSMutableArray*) data;

-(void) updateSpecialproductsModel:(NSMutableArray*) data;

-(void) updateSearchProductsModel:(NSMutableArray*)data;

-(void) updateReviewCommentsModel:(NSMutableArray*)data;


@end


// caching the config file
@interface AppInfoEntity : NSObject {
	
	NSString	*appVersion;
	NSString	*configVersion;
	NSString    *versionUpdateUrl;
	NSArray     *supportedVersions;
	NSDictionary	  *configDict;
    
	///// Support //////////////
	NSDate *appLaunchTime;
	NSDate *appPushbackTime;
	NSDate *appActivatedTime;
	NSDate *appServiceCallTime;
	NSDate *appConfigUpdateTime;
	NSString *serviceName;
	NSString *featureName;
	NSString *deviceToken;
	NSString *configResponseString;
	NSString *configEndpoint;
	////////////////////////////
}

///// Support //////////////
@property(nonatomic, strong) NSDate *appLaunchTime;
@property(nonatomic, strong) NSDate *appPushbackTime;
@property(nonatomic, strong) NSDate *appActivatedTime;
@property(nonatomic, strong) NSDate *appServiceCallTime;
@property(nonatomic, strong) NSDate *appConfigUpdateTime;
@property(nonatomic, strong) NSString *serviceName;
@property(nonatomic, strong) NSString *featureName;
@property(nonatomic, strong) NSString *deviceToken;
@property(nonatomic, strong) NSString *configResponseString;
@property(nonatomic, strong) NSString *configEndpoint;
////////////////////////////

@property(nonatomic, strong) NSString	*appVersion;
@property(nonatomic, strong) NSString	*configVersion;
@property(nonatomic, strong) NSString   *versionUpdateUrl;
@property(nonatomic, strong) NSArray    *supportedVersions;
@property(nonatomic, strong) NSDictionary  *configDict;

-(void) appInfoUpdate;

-(BOOL) isAppVersionSupported:(NSArray*)versions;

@end

//User Data Entity

@interface UserProfileEntity : NSObject {
	
	NSString *username;
	NSString *password;
	
}

@property(nonatomic,copy)NSString *username;

@property(nonatomic,copy)NSString *password;

@end

@interface CatalogService : NSObject {
    
    NSString *productId;
    
    NSString *productName;
    
    NSString *productImageUrl;
    
    NSString *productCount;
    
    NSArray* catalogDict;
}

@property (nonatomic, copy) NSString *productId;

@property (nonatomic, copy) NSString *productName;

@property (nonatomic, copy) NSString *productImageUrl;

@property (nonatomic, copy) NSString *productCount;

@property (nonatomic, copy) NSArray* catalogDict;

-(void) setProperties:(NSMutableDictionary*) dictionary;

@end


@interface productDetailsService : NSObject {
    
    NSString *productDetailId;
    
    NSString *productDetailName;
    
    NSString *productDetailImageUrl;
    
    NSString *productRatingView;
    
    NSString *productDetailsPrice;
}

@property (nonatomic, copy) NSString *productDetailId;

@property (nonatomic, copy) NSString *productDetailName;

@property (nonatomic, copy) NSString *productDetailImageUrl;


@property (nonatomic, copy) NSString  *productDetailsPrice;

@property (nonatomic, copy) NSString *productRatingView;

-(void) setProperties:(NSMutableDictionary*) dictionary;

@end

@interface productService : NSObject {
    
    NSString *productDetailId;
    
    NSString *productDetailName;
    
    NSString *productDetailImageUrl;
    
    NSString *productDescription; 
    
    NSString *productRatingView;
    
    NSString *productDetailsPrice;
    
    NSString *productTVType;
    
    NSString *productScreen; 
    
    NSString *productRatio;
    
    NSString *productDefinition;
}

@property (nonatomic, copy) NSString *productDetailId;

@property (nonatomic, copy) NSString *productDetailName;

@property (nonatomic, copy) NSString *productDetailImageUrl;

@property (nonatomic, copy) NSString *productDescription;

@property (nonatomic, copy) NSString *productDetailsPrice;

@property (nonatomic, copy) NSString *productRatingView;

@property (nonatomic, copy) NSString *productTVType;

@property (nonatomic, copy) NSString *productScreen;

@property (nonatomic, copy) NSString *productRatio;

@property (nonatomic, copy) NSString *productDefinition;

-(void) setProperties:(NSMutableDictionary*) dictionary;

@end


@interface productReview : NSObject {
    
    NSString *productDetailId;
    
    NSString *userComments; 
    
    NSString *productRatingView;
    
    NSString *averageCustomerReview;
    
    NSString *ratingKey;
    
    NSString *ratingValue;
    
    NSString *userName;
    
    NSString *commentedDate;
    
    NSString *productRevUserID;
}

@property (nonatomic, copy) NSString *productDetailId;

@property (nonatomic, copy) NSString *userComments;

@property (nonatomic, copy) NSString *productRatingView;

@property (nonatomic, copy) NSString *averageCustomerReview;

@property (nonatomic, copy) NSString *ratingKey;

@property (nonatomic, copy) NSString *ratingValue;

@property (nonatomic, copy) NSString *userName;

@property (nonatomic, copy) NSString *commentedDate;

@property (nonatomic, copy) NSString *productRevUserID;

-(void) setProperties:(NSMutableDictionary*) dictionary;

@end



@interface specialProducts : NSObject {
    
    NSString *specialProductId;
    
    NSString *specialProductName;
    
    NSString *specialProductImageUrl;
    
    NSString  *specialProductRatingView;
    
    NSString *specialProductPrice;
    
    NSString* specialProductDesc;
}

@property (nonatomic, copy) NSString *specialProductId;

@property (nonatomic, copy) NSString *specialProductName;

@property (nonatomic, copy) NSString *specialProductImageUrl;

@property (nonatomic, copy) NSString  *specialProductRatingView;

@property (nonatomic, copy) NSString *specialProductPrice;

@property (nonatomic, copy) NSString* specialProductDesc; 

-(void) setProperties:(NSMutableDictionary*) dictionary;

@end

@interface searchProducts : NSObject {
    
    NSString *searchProductId;
    
    NSString *searchProductName;
    
    NSString *searchProductImageUrl;
    
    NSString  *searchProductRatingView;
    
    NSString *searchProductPrice;
    
}

@property (nonatomic, copy) NSString *searchProductId;

@property (nonatomic, copy) NSString *searchProductName;

@property (nonatomic, copy) NSString *searchProductImageUrl;

@property (nonatomic, copy) NSString  *searchProductRatingView;

@property (nonatomic, copy) NSString *searchProductPrice;

-(void) setProperties:(NSMutableDictionary*) dictionary;

@end


@interface ReviewComment : NSObject {
    
    NSString *reviewCommentProductId;
    
    NSString *reviewCommentComment;
    
    NSString *reviewCommentUserName;
    
    NSString  *reviewCommentDate;
    
    NSString *reviewCommentRating;
    
}

@property (nonatomic, copy) NSString *reviewCommentProductId;

@property (nonatomic, copy) NSString *reviewCommentComment;

@property (nonatomic, copy) NSString *reviewCommentUserName;

@property (nonatomic, copy) NSString  *reviewCommentDate;

@property (nonatomic, copy) NSString *reviewCommentRating;

-(void) setProperties:(NSMutableDictionary*) dictionary;

@end




