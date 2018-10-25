


angular.module('todoApp', ['rzModule'])
  .controller('TodoListController', function($scope) {

    var todoList = this;
    $scope.fixtures = []

    $scope.getFixtures = function(){
    // check if there is query in url
    // and fire search in case its value is not empty

      var xmlHttp = new XMLHttpRequest();
      xmlHttp.open( "GET", "/api/patch/fixtures", false ); // false for synchronous request
      xmlHttp.send( null );
      xmlHttp.onload = function () {

          console.log(xmlHttp.responseText)
      };
      console.log($scope.fixtures);
      $scope.fixtures = JSON.parse(xmlHttp.responseText);
    };

    $scope.slider_callbacks = {
        value: 255,
        options: {
          floor: 0,
          ceil: 255,
          onStart: function(id, newValue, highValue, pointerType) {
            console.info('start', id, newValue, pointerType)
            $scope.otherData.start = newValue * 10
          },
          onChange: function(id, newValue, highValue, pointerType) {
            var xhr = new XMLHttpRequest();
            xhr.open('POST', '/api/patch/fixture/value', true);
            xhr.setRequestHeader('Content-type', 'application/javascript');
            msg = { "identity": "BLUE", "value": newValue, "channel": 0 }

            xhr.send(JSON.stringify(msg));

            console.log($scope.fixtures);
            $scope.otherData.change = newValue * 10
          },
          onEnd: function(id, newValue, highValue, pointerType) {
            console.info('end', id, newValue, pointerType)
            todoList.getpatch();
          },
        },
      }

  $scope.otherData = {
    start: 0,
    change: 0,
    end: 0,
  }




    todoList.todos = [
      {text:'learn AngularJS', done:true},
      {text:'build an AngularJS app', done:false}];
 
    todoList.addTodo = function() {
      todoList.todos.push({text:todoList.todoText, done:false});
      todoList.todoText = '';
    };
 
    todoList.remaining = function() {
      var count = 0;
      angular.forEach(todoList.todos, function(todo) {
        count += todo.done ? 0 : 1;
      });
      return count;
    };

//    todoList.archive = function() {
//       var oldTodos = todoList.todos;
//       todoList.todos = [];
//       angular.forEach(oldTodos, function(todo) {
//         if (!todo.done) todoList.todos.push(todo);
//       });
//     };

    todoList.colour = function httpGetAsync(theUrl, callback){
        theUrl = "/api/" + theUrl

        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open( "GET", theUrl, true ); // false for synchronous request
        xmlHttp.send( null );
        //return xmlHttp.responseText;
        todoList.getpatch()
   };



    todoList.patch = []
   todoList.getpatch = function httpGetAsync(callback){
           theUrl = "/api/patch"

            var xmlHttp = new XMLHttpRequest();
            xmlHttp.open( "GET", theUrl, true ); // false for synchronous request
            xmlHttp.send( null );
            console.log(xmlHttp.responseText);
            xmlHttp.onload = function () {
                todoList.patch = JSON.parse(xmlHttp.responseText);
                //console.log(todoList.patch);
                // do something to response
            };

           //return xmlHttp.responseText;
      };

    $scope.init = function () {
          $scope.getFixtures();
          console.log("Init");
        // check if there is query in url
        // and fire search in case its value is not empty

          var xmlHttp = new XMLHttpRequest();
          xmlHttp.open( "GET", "/api/patch/fixtures", false ); // false for synchronous request
          xmlHttp.send( null );
          xmlHttp.onload = function () {

              console.log(xmlHttp.responseText)
          };
          console.log($scope.fixtures);
          $scope.fixtures = JSON.parse(xmlHttp.responseText);
    };
    $scope.init();

  });

//  $http.get('http://www.viudadesoubrier.com/angular/model.php')
//          .success(function(data) {
//              $scope.names = eval(data);
//              console.log(data)
//          })
//          .error(function(data) {
//              alert(data);
//              console.log('Error: ' + data);
//          });