<!-- form.html -->
<div class="row">
    <div class="col-sm-8 col-sm-offset-2">
        <div id="form-container">
            <div class="page-header text-center">
                <h2>User Order</h2>
                <!-- the links to our nested states using relative paths -->
                <!-- add the active class if the state matches our ui-sref -->
                <div id="status-buttons" class="text-center">
                    <a ui-sref-active="active" ui-sref=".address"><span>1</span> </a>
                    <a ui-sref-active="active" ui-sref=".order"><span>2</span> </a>
                </div>
            </div>

            <!-- use ng-submit to catch the form submission and use our Angular function -->
            <form id="signup-form" ng-submit="octrl.confirmOrder()">

                <!-- our nested state views will be injected here -->
                <div id="form-views" ui-view></div>
            </form>
        </div>
    </div>
</div>