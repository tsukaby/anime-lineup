///<reference path="./typings/angularjs/angular.d.ts" />
///<reference path="./typings/angular-ui/angular-ui-router.d.ts" />

///<reference path="Model.ts" />
///<reference path="Filter.ts" />
///<reference path="Service.ts" />
///<reference path="Controller.ts" />
///<reference path="Directive.ts" />

module App {
    "use strict";

    export var appName = "animeLineup";

    angular.module(
        appName,
        [
            "ui.router",
            "ngCookies",
            "ngResource",
            "ngSanitize",
            "ngRoute",
            "ui.bootstrap",
            "duScroll",
            appName + ".controller",
            appName + ".service",
            appName + ".filter",
            appName + ".directive"

        ],
        ($stateProvider:ng.ui.IStateProvider, $sceDelegateProvider:ng.ISCEDelegateProvider, $locationProvider:ng.ILocationProvider, $httpProvider:ng.IHttpProvider) => {
            $sceDelegateProvider.resourceUrlWhitelist([
                "self",
                "http://www.facebook.com/**",
                "https://www.facebook.com/**"
            ]);

            $stateProvider
                .state("/", {
                    url: "/?year&seasonType",
                    controller: "AnimeListController",
                    templateUrl: "/partials/anime_list.html"
                })
                .state("/_=_", {
                    // passportを使ったFacebook認証の場合#_=_にリダイレクトされてしまうため、以下のように正しいパスに誘導する
                    redirectTo: "/"
                })
                .state("/help", {
                    url: "/help",
                    controller: "HelpController",
                    templateUrl: "/partials/help.html"
                })
                .state("/login", {
                    url: "/login",
                    controller: "LoginController",
                    templateUrl: "/partials/login.html"
                })
                .state("/register_anime", {
                    url: "/register_anime",
                    controller: "RegisterAnimeController",
                    templateUrl: "/partials/register_anime.html"
                })
                .state("/register_anime/:year/:season/:success", {
                    url: "/register_anime/:year/:season/:success",
                    controller: "RegisterAnimeController",
                    templateUrl: "/partials/register_anime.html"
                })
                .state("/season_calendar", {
                    url: "/season_calendar",
                    controller: "SeasonController",
                    templateUrl: "/partials/season_list.html"
                });

            $locationProvider.html5Mode(true);

            // Intercept 401s and redirect you to login
            $httpProvider.interceptors.push(["$q", "$location", ($q:ng.IQService, $location:ng.ILocationService) => {
                return {
                    "responseError": (response:any) => {
                        if (response.status === 401) {
                            $location.path("/login");
                            return $q.reject(response);
                        } else {
                            return $q.reject(response);
                        }
                    }
                };
            }]);
        }
    ).run(($rootScope:any, $location:ng.ILocationService, AuthService:Service.AuthService) => {
            $rootScope.season = {};

            // Redirect to login if route requires auth and you"re not logged in
            $rootScope.$on("$routeChangeStart", (event:any, next:any) => {
                if (next.authenticate && !AuthService.isLoggedIn()) {
                    $location.path("/login");
                }
            });
        });

    angular.module(
        appName + ".service",
        [],
        ()=> {
            false;
        }
    )
        .factory("AnimeSearchService", ($http:ng.IHttpService, $rootScope:ng.IRootScopeService):Service.AnimeSearchService => {
            return new Service.AnimeSearchService($http, $rootScope);
        })
        .factory("AuthService", ($location:ng.ILocationService, $rootScope:any, $cookieStore:any, $http:ng.IHttpService):Service.AuthService => {
            return new Service.AuthService($location, $rootScope, $cookieStore, $http);
        })
        .factory("NavigationService", ($rootScope:any, SeasonService:Service.SeasonService):Service.NavigationService => {
            return new Service.NavigationService($rootScope, SeasonService);
        })
        .factory("SeasonService", ($http:ng.IHttpService):Service.SeasonService => {
            return new Service.SeasonService($http);
        })
    ;

    angular.module(
        appName + ".controller",
        [appName + ".service"],
        ()=> {
            false;
        }
    )
        .controller("AnimeListController", AnimeListControllerModule.AnimeListController)
        .controller("AnimeSearchController", AnimeSearchControllerModule.AnimeSearchController)
        .controller("HelpController", HelpControllerModule.HelpController)
        .controller("LoginController", LoginControllerModule.LoginController)
        .controller("ModalInstanceController", ModalInstanceControllerModule.ModalInstanceController)
        .controller("RegisterAnimeController", RegisterAnimeControllerModule.RegisterAnimeController)
        .controller("SeasonController", SeasonControllerModule.SeasonController)
    ;

    angular.module(appName + ".filter", [], () => {
        false;
    })
        .filter("SeasonColorFilter", () => Filter.SeasonColorFilter())
        .filter("SeasonNameFilter", () => Filter.SeasonNameFilter())
        .filter("ViewingSortFilter", () => Filter.ViewingSortFilter())
        .filter("UrlEncodeFilter", () => Filter.UrlEncodeFilter())
    ;

    angular.module(appName + ".directive", [], () => {
        false;
    })
    ;
}
