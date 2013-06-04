(defproject sshgc "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [clojure-jsr223 "1.0"]
                 [local.oracle/javafxrt "2.2.21"]
                 [local/FXDialog "1.3"]
                 [com.google.com.p.expect4j/expect4j "1.0"]]
  :main sshgc.core
  :aot :all)
