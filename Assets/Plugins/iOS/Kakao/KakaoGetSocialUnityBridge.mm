// #include "KakaoGetSocialBridgeUtils.h"
#import <KakaoOpenSDK/KakaoOpenSDK.h>

extern "C" {
    void _sendKakaoLogin()
    {
        // Close old session
        if ( ! [[KOSession sharedSession] isOpen] ) {
            NSLog(@"in isOpen condition");
            [[KOSession sharedSession] close];
            NSLog(@"Old session closed");
        }

        // session open with completion handler
        [[KOSession sharedSession] openWithCompletionHandler:^(NSError *error) {
            if (error) {
                NSLog(@"login failed. - error: %@", error);
                UnitySendMessage("AndroidPlugin","PrintResult",[[error localizedDescription] UTF8String]);

            }
            else {
                NSLog(@"login succeeded.");
                NSString *_token = [[NSString alloc] initWithString:KOSession.sharedSession.token.accessToken];
                                UnitySendMessage("AndroidPlugin","PrintResult",[_token UTF8String]);
            }
        }];
    }

    void _sendKakaoLogout()
    {
        [[KOSession sharedSession] logoutAndCloseWithCompletionHandler:^(BOOL success, NSError *error) {
            if (error) {
                NSLog(@"failed to logout. - error: %@", error);
                UnitySendMessage("SignInPassApp(Clone)","KakaoPluginIOSLog",[[error localizedDescription] UTF8String]);
            }
            else {
                NSLog(@"logout succeeded.");
                UnitySendMessage("SignInPassApp(Clone)","KakaoPluginIOSLog","success");
            }
        }];
    }
}
