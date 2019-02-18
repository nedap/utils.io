(defproject com.nedap.staffing-solutions/utils.io "0.1.1"
  :description "I/O utilities for Clojure, filling missing parts from clojure.java.io."
  :url "https://github.com/nedap/utils.io"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :repositories {"releases"       {:url      "https://nedap.jfrog.io/nedap/staffing-solutions/"
                                   :username :env/artifactory_user
                                   :password :env/artifactory_pass}}
  :deploy-repositories [["releases" {:url "https://nedap.jfrog.io/nedap/staffing-solutions/"
                                     :sign-releases false}]]
  :repository-auth {#"https://nedap.jfrog\.io/nedap/staffing-solutions/"
                    {:username :env/artifactory_user
                     :password :env/artifactory_pass}}
  :dependencies [[com.nedap.staffing-solutions/utils.spec "0.1.1"]
                 [me.raynes/fs "1.4.6"]
                 [org.clojure/clojure "1.10.0"]])
