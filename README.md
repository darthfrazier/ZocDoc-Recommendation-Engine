# ZocDoc-Recommendation-Engine

This is a recommendation engine that simulates how ZocDoc may recommend doctors to its users. It has a simple web interface where the user inputs his or her information and symptoms. The algorithm then assigns the user to a cluster based on a kmeans model. From the cluster we can glean what doctors users similar to the user rated highly and then suggest them to the user.

The kmeans model is generated and trained on the java backend when the web page loads. I generated 1000 entries of sample data to train the model. Runtime and space efficiency are unknown, as I did not have time to analyze my code (5 - 10 hour limit).

The app is running on IBM's Bluemix. This is my first time using this platform and I am very impressed. It has a lot of functionality, and virtually any application on any stack can be built on it. After learning how to use it, I created a java Websphere application. I normally would never use a java servlet stack to build an application, but I wanted to use the Weka ML library, which is only in java. This lack of experience, with Java servlets led to a slew of very frustrating bug. Surprisingly though, I had a much easier time with the ML algorithms. I used weka's simplekmeans to cluster my users. The concept is explained well here http://bipublication.com/files/IJCMS-V4I1-2013-16.pdf.

The main challenge for this application was getting proper data. With real live data from zocdoc, my results would be worth much more. Real life data has real patterns that will be picked up on by ML algorithms. The data I generated was psuedorandom and I had limited control over the relationship and patterns in the data. So this app works largely as a proof of concept. You can indeed cluster users and use that to help recommend things to new users.

If I were to spend more time on this, out side of cleaning up my code, I would try to source real data. This would allow me to train a more useful model. Then I would build out a more robust client so that there are more options for the user to input, as there would be in a practical application (e.g. more symptoms).

This was a great challenge, and I hoped to do something a little different than what you expected. My code isnt perfect, especially considering the time limit, but I am proud of the work I produced and what I learned. I hope you can appreciate my application.
