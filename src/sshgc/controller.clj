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

(def xnest-exist?
  (command-exist? "Xnest"))

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

(defn ssh
  "ssh connect commands."
  [account ip password disp]
  (str "DISPLAY=:" disp " ssh -X -Y " account "@" ip " gnome-session"))

(defn run-sh
  [x]
  (future (sh "bash" "-c" x)))

(defn create-display
  "Create display if Xnest or Xephry exist."
  [window-size disp]
  (cond xephyr-exist? (run-sh (xephyr window-size disp))
        xnest-exist?  (run-sh (xnest  window-size disp))
        :else (dependancy-missing)))

(defn ssh-connect
  "Connect ssh with X tunnel."
  [account ip password disp]
  (println (run-sh (ssh account ip password disp))))
