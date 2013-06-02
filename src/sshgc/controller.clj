(ns sshgc.controller
  (:gen-class)
  (:import [javafx.fxml FXML]
           [javafx.scene Scene]
           [javafx.event ActionEvent]
           [javafx.scene.text Text])
  (:require [sshgc.dialog :as dialog])
  (:use [clojure.java.shell :only [sh]]))

(defn dependancy-missing
  "Show dependancy missing dialog."
  []
  (dialog/error "Dependance Missing" "Please install Xnet or Xeyphr"))

(defn command-exist?
  "Check if command exist on system."
  [x]
  (> 2 (:exit (sh x))))

(def xephyr-exist?
  (command-exist? "Xephyr"))

(defn xephyr
  "Xephry commands."
  [window-size disp]
  (let [size (if (= window-size "fullscreen") "-fullscreen"
                 (str "-screen " window-size))]
  (str "Xephyr -ac " size " -br -reset -terminate 2> /dev/null " ":" disp " &")))

(defn xnest
  "Xnest commands."
  [window-size disp]
  (str "Xnest -geometry " window-size " :" disp " &"))

(defn create-display
  "Create display if Xnest or Xephry exist."
  [window-size disp]
  (let [d (str ":" disp)
        run-sh (fn [x] (future (sh "bash" "-c" x)))]

    (cond (command-exist? "Xephyr") (run-sh (xephyr window-size disp))
          (command-exist? "Xnest") (run-sh (xnest window-size disp))
          :else (dependancy-missing))
    ))
