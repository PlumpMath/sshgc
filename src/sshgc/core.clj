(ns sshgc.core
  (:import (javafx.application Application Platform)
           (javafx.fxml FXMLLoader)
           (javafx.event ActionEvent EventHandler)
           (javafx.scene Scene)
           (javafx.stage Stage)))
;; (javafx.scene Parent Scene)
;; (javafx.stage Stage StageStyle)))

(gen-class
 :name sshgc.core
 :main true
 :extends javafx.application.Application)

(defn -main [& args]
  (Application/launch sshgc.core args))

(defn -start [this stage]
  (let [loc (clojure.java.io/resource "mainwindow.fxml")
        root (FXMLLoader/load loc)]

    (.setScene stage (Scene. root))
    (.setTitle stage "sshgc")
    (.show stage)

    (.setOnCloseRequest stage
                        (proxy [EventHandler] []
                          (handle [^ActionEvent event]
                            (.hide stage)
                            (Platform/exit))))
    ))
