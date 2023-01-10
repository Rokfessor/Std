import 'dart:developer';

import 'package:flutter/material.dart';

class SettingsWidget extends StatefulWidget {
  final bool isActive;
  final Settings settings;

  const SettingsWidget(
      {super.key, required this.isActive, required this.settings});

  @override
  State<StatefulWidget> createState() => SettingsWidgetState();
}

class SettingsWidgetState extends State<SettingsWidget> {
  @override
  Widget build(BuildContext context) {
    if (!widget.isActive) {
      return Container();
    }

    return Container(
        height: 250,
        padding: const EdgeInsets.all(15),
        decoration: const BoxDecoration(
          borderRadius: BorderRadius.all(Radius.circular(10)),
          color: Color.fromARGB(200, 0, 0, 0),
        ),
        child: Align(
            alignment: FractionalOffset.topLeft,
            child: Column(
              children: _getByType(),
            )));
  }

  List<Row> _getByType() {
    List<Row> list = [];

    switch (widget.settings.effectType) {
      case EffectType.seamCarving:
        {
          list.addAll([
            Row(
              children: [
                const Text(
                  "Compress by width",
                  style: TextStyle(color: Colors.white),
                ),
                const Spacer(),
                Slider(
                    value: widget.settings.maxWidth.toDouble(),
                    min: 10,
                    max: 100,
                    onChanged: (double value) => setState(
                        () => widget.settings.maxWidth = value.toInt()))
              ],
            ),
            Row(
              children: [
                const Text(
                  "Compress by height",
                  style: TextStyle(color: Colors.white),
                ),
                const Spacer(),
                Slider(
                    value: widget.settings.maxHeight.toDouble(),
                    min: 10,
                    max: 100,
                    onChanged: (double value) => setState(
                        () => widget.settings.maxHeight = value.toInt()))
              ],
            ),
            Row(
              children: [
                const Text(
                  "Save size",
                  style: TextStyle(color: Colors.white),
                ),

                Checkbox(
                    value: widget.settings.saveScale,
                    onChanged: (bool? value) =>
                        setState(() => widget.settings.saveScale = value!))
              ],
            )
          ]);
        }
    }

    return list;
  }
}

class Settings {
  String effectType;
  int maxWidth;
  int maxHeight;
  bool saveScale;
  String image;

  Settings(
      {required this.effectType,
      this.maxHeight = 50,
      this.maxWidth = 50,
      this.saveScale = true,
      this.image = ""});

  static Settings getDefault() {
    return Settings(effectType: EffectType.seamCarving);
  }

  @override
  String toString() {
    return '{"effectType": "$effectType", "height": $maxHeight, "width": $maxWidth, "saveScale": $saveScale, "image":"$image"}';
  }
}

class EffectType {
  static const String seamCarving = "SeamCarving";
  static const String monochrome = "Monochrome";
}