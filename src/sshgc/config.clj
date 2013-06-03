(ns sshgc.config
  (:require [clojure.java.io]))

(def config-file "~/.config/sshgc.clj")

(defn save [conf]
  (spit config-file conf))

(defn read []
  (read-string (slurp config-file)))
