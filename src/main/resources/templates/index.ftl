<!DOCTYPE html>
<html lang="en" ng-app="fdpApp">
    <head>
        <title>${title}</title>
        <link href="css/bootstrap.css" rel="stylesheet"/>
        <link href="css/app.css" rel="stylesheet"/>
        <link href="css/multistep.css" rel="stylesheet"/>
        <link href="css/angular-material.css" rel="stylesheet" >
    </head>

    <style>
        body {
            margin: 0;
            font-family: 'Lato', sans-serif;
            background-color: orange;
        }

        .overlay {
            height: 100%;
            width: 100%;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: rgb(0,0,0);
            background-color: rgba(51, 51, 51, 0.8);
            overflow-x: hidden;
            transition: 0.5s;
        }

        .overlay-content {
            position: relative;
            top: 25%;
            width: 100%;
            text-align: center;
            margin-top: 30px;
        }

        .overlay a {
            padding: 8px;
            text-decoration: none;
            font-size: 36px;
            color: rgb(193,148,101);
        ;
            display: block;
            transition: 0.3s;
        }

        .overlay a:hover, .overlay a:focus {
            color: #f1f1f1;
        }

        .overlay .closebtn {
            position: absolute;
            top: 20px;
            right: 45px;
            font-size: 60px;
        }

        @media screen and (max-height: 450px) {
            .overlay a {
                font-size: 20px;
            }

            .overlay .closebtn {
                font-size: 40px;
                top: 15px;
                right: 35px;
            }
        }

        .img-circle {
            display: block;
            margin: 0 auto;
        }
    </style>

    <body>
    <div class="example3">
        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar3">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div id="navbar3" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <#--<li class="dropdown">-->
                            <#--<a href="/FastDeliveryPizza/#/restaurant" class="dropdown-toggle" role="button" aria-expanded="true"><span class="glyphicon glyphicon-user"></span></a>-->
                        <#--</li>-->
                        <li class="dropdown2">
                            <a href="/FastDeliveryPizza/#/product" class="dropdown-toggle" role="button" aria-expanded="true"><span class="glyphicon glyphicon-lock"></span></a>
                        </li>
                        <li class="dropdown2">
                            <a href="/FastDeliveryPizza/#/userorderpartials/address" class="dropdown-toggle" role="button" aria-expanded="true"><span class="glyphicon glyphicon-shopping-cart"></span></a>
                        </li>
                    </ul>
                </div>
                <!--/.nav-collapse -->
            </div>
            <!--/.container-fluid -->
        </nav>
    </div>
    <div class="jumbotron">
        <div class="container">
            <#--<img src="images/logo.png" allign="center">-->
                <!--<div ng-view></div>-->
                <div ui-view></div>
        </div>
    </div>
        <script src="js/lib/angular.min.js" ></script>
        <script src="js/lib/angular-ui-router.min.js" ></script>
        <script src="js/lib/angular-material.js" ></script>
        <script src="js/lib/ngStorage.min.js"></script>
        <script src="js/lib/localforage.min.js" ></script>
        <script src="js/app/app.js"></script>
        <script src="js/app/modules/product/ProductService.js"></script>
        <script src="js/app/modules/product/ProductController.js"></script>
        <script src="js/app/modules/restaurant/RestaurantService.js"></script>
        <script src="js/app/modules/restaurant/RestaurantController.js"></script>
        <script src="js/app/modules/productRestaurant/ProductRestaurantService.js"></script>
        <script src="js/app/modules/productRestaurant/ProductRestaurantController.js"></script>
        <script src="js/app/modules/order/OrderService.js"></script>
        <script src="js/app/modules/order/OrderController.js"></script>
    </body>
</html>